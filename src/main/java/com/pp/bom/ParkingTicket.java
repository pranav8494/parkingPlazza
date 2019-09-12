package com.pp.bom;

import org.joda.time.DateTime;

import com.pp.bom.parking.ParkingSlot;
import com.pp.bom.vehicle.Car;

public class ParkingTicket {
	
	private final String id;
	private final Car car;
	private final ParkingSlot slot;
	private final DateTime entryDateTime;
	private DateTime exitTime = null;
	
	public ParkingTicket(Car car, ParkingSlot slot){
		this.car = car;
		this.slot = slot;
		this.id = car.getRegistrationId() + "_" + slot.getId() + "_" + this.entryDateTime;
		this.entryDateTime = DateTime.now();
	}
	
	public String getId(){
		return this.id;
	}
	
	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @return the slot
	 */
	public ParkingSlot getSlot() {
		return slot;
	}

	/**
	 * @return the entryDateTime
	 */
	public DateTime getEntryDateTime() {
		return entryDateTime;
	}

	/**
	 * @return the exitTime
	 */
	public DateTime getExitTime() {
		return exitTime;
	}

	/**
	 * Sets the exit time for a Car on the {@link ParkingTicket}.
	 */
	public final boolean setExitTime(){
		if(this.exitTime == null){
			this.exitTime = DateTime.now();
			return true;
		}
		else{
			throw new IllegalStateException("Car already maked as exiting, Can't exit a car once it already exited :P");
		}
	}
}
