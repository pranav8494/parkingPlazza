package com.pp.bom.parking;

import java.util.UUID;

import org.joda.time.DateTime;

import com.google.common.base.Optional;
import com.pp.bom.ParkingTicket;
import com.pp.bom.vehicle.Car;

/**
 * Class to represent a ParkingSlot.
 * 
 * @author ppandey
 *
 */
public abstract class ParkingSlot {

	/**
	 * Available Parking slots types in system.
	 * 
	 * @author ppandey
	 *
	 */
	public enum SlotType {
		GASOLINE, ELECTRIC_20KW, ELECTRIC_50KW
	}

	private final String id;
	private final SlotType slotType;
	private Optional<Car> assignedCar;
	private boolean isSlotFree = true;

	/**
	 * Constructor. Given ID is appended with a randomly generated unique ID
	 * text.
	 * 
	 * @param id
	 * @param slotType
	 */
	public ParkingSlot(String id, SlotType slotType) {

		this.id = id + "_" + UUID.randomUUID().toString();
		this.slotType = slotType;
	}

	/**
	 * Gets the ID of the Parking slot.
	 * 
	 * @return
	 */
	public String getId() {

		return this.id;
	}

	/**
	 * Get the {@link SlotType}.
	 * 
	 * @return
	 */
	public SlotType getSlotType() {
		return this.slotType;
	}

	/**
	 * Assigns the {@link ParkingSlot} to the given car if the slot is Free.
	 * 
	 * @param car
	 * @return {@link Optional} of {@link ParkingTicket} if slot is free, else
	 *         {@link Optional} absent.
	 */
	public Optional<ParkingTicket> assignParkingSlotToCar(Car car) {

		if (isSlotFree) {
			this.assignedCar = Optional.fromNullable(car);
			this.isSlotFree = false;
			return Optional.fromNullable(new ParkingTicket(car, this));
		} else {
			return Optional.absent();
		}
	}

	/**
	 * Release a slot and updates it accordingly.
	 * 
	 * @return {@link Optional} of {@link DateTime} representing the slot
	 *         release time, else {@link Optional} absent (you can't release
	 *         what is already free \m/).
	 */
	public Optional<DateTime> releaseSlot() {
		if (isSlotFree) {
			return Optional.absent();
		} else {
			this.assignedCar = Optional.absent();
			this.isSlotFree = true;
			return Optional.fromNullable(DateTime.now());
		}
	}

	/**
	 * Class to instantiate Parking slot for Gasoline cars.
	 * 
	 * @author ppandey
	 */
	public class ParkingSlotForGasoline extends ParkingSlot {

		/**
		 * Constructor.
		 * 
		 * @param id
		 */
		public ParkingSlotForGasoline(String id) {
			super(id, SlotType.GASOLINE);
		}
	}

	/**
	 * Class to instantiate Parking slot for Electric 20KW cars.
	 * 
	 * @author ppandey
	 */
	public class ParkingSlotForElectric20kw extends ParkingSlot {

		/**
		 * Constructor.
		 * 
		 * @param id
		 */
		public ParkingSlotForElectric20kw(String id) {
			super(id, SlotType.ELECTRIC_20KW);
		}
	}

	/**
	 * Class to instantiate Parking slot for Electric 50KW cars.
	 * 
	 * @author ppandey
	 */
	public class ParkingSlotForElectric50kw extends ParkingSlot {

		/**
		 * Constructor.
		 * 
		 * @param id
		 */
		public ParkingSlotForElectric50kw(String id) {
			super(id, SlotType.ELECTRIC_50KW);
		}
	}
}
