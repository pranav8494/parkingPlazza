package com.pp.bom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.pp.bom.parking.ParkingForElectric20KW;
import com.pp.bom.parking.ParkingForElectric50KW;
import com.pp.bom.parking.ParkingForGasolineCar;
import com.pp.bom.parking.ParkingSlot;
import com.pp.bom.pricingPolicy.ParkingRate;
import com.pp.bom.vehicle.Car;

public class TollParking {

	protected static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormat.forPattern("yyyyMMdd");

	private static final Logger LOG = LoggerFactory.getLogger(TollParking.class);

	private final Address tollParkingAddress;
	private final String tollParkingName;
	private ParkingRate parkingRate;

	private Map<CarTypeEnum, Integer> parkingSlotCounter = new HashMap<CarTypeEnum, Integer>();
	private ImmutableMap<CarTypeEnum, Integer> maxSlotCapacity;

	private Table<CarTypeEnum, ParkingSlot, Boolean> parkingSlotTable = HashBasedTable.create();
	private Map<String, Car> carMap = new HashMap<String, Car>();
	private Map<String, String> parkingAssignmentMap = new HashMap<String, String>();
	private Map<String, ParkingTicket> parkingTicketMapByCar = new HashMap<String, ParkingTicket>();
	private Map<String, ParkingTicket> archivedParkingTickets = new HashMap<String, ParkingTicket>();

	public TollParking(String tollParkingName, Address tollParkingAddress,
			HashMap<CarTypeEnum, Integer> maxSlotCapacity, ParkingRate rate) {

		this.tollParkingName = tollParkingName;
		this.tollParkingAddress = tollParkingAddress;
		this.parkingRate = rate;
		parkingSlotCounter.putAll(maxSlotCapacity);
		this.maxSlotCapacity = ImmutableMap.copyOf(maxSlotCapacity);
		initSlotMap();
	}

	/**
	 * @return {@link Address} of the parking toll.
	 */
	public final Address getAddress() {
		return this.tollParkingAddress;
	}

	/**
	 * @return the Name of parking toll.
	 */
	public final String getTollParkingName() {
		return this.tollParkingName;
	}

	/**
	 * @return the parkingRate
	 */
	public final ParkingRate getParkingRate() {
		return parkingRate;
	}

	/**
	 * @param parkingRate
	 *            the parkingRate to set
	 */
	public final void setParkingRate(ParkingRate parkingRate) {
		this.parkingRate = parkingRate;
	}

	/**
	 * Initialize the {@link ParkingSlot}s available in the parking lot.
	 */
	private void initSlotMap() {

		for (Entry<CarTypeEnum, Integer> e : maxSlotCapacity.entrySet()) {
			switch (e.getKey()) {
			case GASOLINE:
				for (int i = 0; i < e.getValue(); i++) {
					addParkingSlot(new ParkingForGasolineCar(Integer.toString(i)));
				}
				break;
			case ELECTRIC_20KW:
				for (int i = 0; i < e.getValue(); i++) {
					addParkingSlot(new ParkingForElectric20KW(Integer.toString(i)));
				}
				break;
			case ELECTRIC_50KW:
				for (int i = 0; i < e.getValue(); i++) {
					addParkingSlot(new ParkingForElectric50KW(Integer.toString(i)));
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Does all necessary steps to add a parking slot in the parking lot.
	 * 
	 * @param slot
	 */
	private void addParkingSlot(ParkingSlot slot) {

		this.parkingSlotTable.put(slot.getSlotType(), slot, true);
	}

	/**
	 * Request for a {@link ParkingSlot} in the parling toll. Return a
	 * {@link Optional} {@link ParkingTicket} with the slot assigned. If No slot
	 * available an {@link Optional} absent is returned.
	 * 
	 * @param car
	 * @return
	 */
	public Optional<ParkingTicket> requestParking(Car car) {

		Optional<ParkingTicket> result = Optional.absent();

		// Checking if car is already in system. Could enhance to check for
		// unpaid bill in archived bills.
		if (this.parkingAssignmentMap.values().contains(car.getRegistrationId())) {
			throw new IllegalStateException("Car already assigned a parking slot.");
		}
		Optional<ParkingSlot> slot = getFreeParkingSlotByType(car.getType());
		if (slot.isPresent()) {

			result = Optional.fromNullable(new ParkingTicket(car, slot.get()));
			this.carMap.put(car.getRegistrationId(), car);
			this.parkingTicketMapByCar.put(car.getRegistrationId(), result.get());
			this.parkingAssignmentMap.put(slot.get().getId(), car.getRegistrationId());
			this.parkingSlotTable.put(slot.get().getSlotType(), slot.get(), false);
		}
		return result;
	}

	/**
	 * Get the first available {@link ParkingSlot} of the given type.
	 * 
	 * @param type
	 * @return
	 */
	private Optional<ParkingSlot> getFreeParkingSlotByType(CarTypeEnum type) {

		for (Entry<ParkingSlot, Boolean> e : this.parkingSlotTable.row(type).entrySet()) {
			if (e.getValue()) {
				return Optional.fromNullable(e.getKey());
			}
		}
		return Optional.absent();
	}

	public Optional<String> exitParking(Car car) {
		long result = 0;

		ParkingTicket ticket = this.parkingTicketMapByCar.get(car.getRegistrationId());
		if(ticket != null){
		result = this.parkingRate.computeCharges(car, ticket.setExitDateTime().toStandardMinutes().getMinutes());

		ticket.setBillingAmount(result);
		this.archivedParkingTickets.put(ticket.getId(), ticket);
		this.parkingTicketMapByCar.remove(car.getRegistrationId());
		this.parkingAssignmentMap.remove(ticket.getSlot().getId(), car.getRegistrationId());
		this.parkingSlotTable.put(ticket.getSlot().getSlotType(), ticket.getSlot(), true);

		return Optional.fromNullable(ticket.getId());
		}
		else{
			return Optional.absent();
		}
	}

	/**
	 * Get the billing amount based on id.
	 * 
	 * @param ticketId
	 * @return
	 */
	public long getBillingAmount(String ticketId) {
		ParkingTicket parkingTicket = this.archivedParkingTickets.get(ticketId);
		if (parkingTicket != null) {
			return parkingTicket.getBillingAmount();
		}
		return 0;
	}

	/**
	 * Check if a bill is paid or not.
	 * 
	 * @param ticketId
	 * @return
	 */
	public boolean isBillPaid(String ticketId) {

		ParkingTicket parkingTicket = this.archivedParkingTickets.get(ticketId);
		if (parkingTicket != null) {
			return parkingTicket.isBillPaid();
		}

		return true;
	}

	/**
	 * Mark a bill paid.
	 * 
	 * @param ticketId
	 * @return
	 */
	public boolean payBill(String ticketId) {

		ParkingTicket parkingTicket = this.archivedParkingTickets.get(ticketId);
		if (parkingTicket != null) {
			parkingTicket.setIsBillPaid(true);
		}

		return true;
	}

	/**
	 * Return the table with {@link ParkingTicket} id, Car registration ID, and
	 * billing amount. If billing amount is 0, then car is still in parking.
	 * 
	 * @return
	 */
	public Table<String, String, Long> getAllUnpaidBillDetail() {
		Table<String, String, Long> result = HashBasedTable.create();

		for (Entry<String, ParkingTicket> e : this.archivedParkingTickets.entrySet()) {
			if (!e.getValue().isBillPaid()) {
				result.put(e.getValue().getId(), e.getValue().getCar().getRegistrationId(),
						e.getValue().getBillingAmount());
			}
		}

		return result;
	}

}
