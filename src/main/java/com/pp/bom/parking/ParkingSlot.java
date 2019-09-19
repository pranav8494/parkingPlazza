package com.pp.bom.parking;

import java.util.UUID;

import org.joda.time.DateTime;

import com.google.common.base.Optional;
import com.pp.bom.CarTypeEnum;
import com.pp.bom.ParkingTicket;
import com.pp.bom.vehicle.Car;

/**
 * Class to represent a ParkingSlot.
 * 
 * @author ppandey
 *
 */
public abstract class ParkingSlot {

	private final String id;
	private final CarTypeEnum slotType;

	/**
	 * Constructor. Given ID is appended with a randomly generated unique ID
	 * text.
	 * 
	 * @param id
	 * @param slotType
	 */
	protected ParkingSlot(CarTypeEnum slotType) {

		this.id = UUID.randomUUID().toString();
		this.slotType = slotType;
	}
	
	/**
	 * Constructor. Given ID is appended with a randomly generated unique ID
	 * text.
	 * 
	 * @param id
	 * @param slotType
	 */
	protected ParkingSlot(String id, CarTypeEnum slotType) {

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
	public CarTypeEnum getSlotType() {
		return this.slotType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{id: " + this.id + ", type: " + this.slotType.name() + "}";
	}
}
