package com.pp.bom;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.pp.bom.parking.ParkingSlot;
import com.pp.bom.vehicle.Car;

public class ParkingTicket {

	private final String id;
	private final Car car;
	private final ParkingSlot slot;
	private final DateTime entryDateTime;
	private DateTime exitDateTime = null;
	private Duration minutesParked = Duration.ZERO;
	private long billingAmount = 0;
	private boolean isBillPaid = false;

	public ParkingTicket(Car car, ParkingSlot slot) {
		this.car = car;
		this.slot = slot;
		this.entryDateTime = DateTime.now();
		this.id = car.getRegistrationId() + "_" + slot.getId() + "_" + this.entryDateTime;
	}

	public String getId() {
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
	public DateTime getExitDateTime() {
		return exitDateTime;
	}

	protected final void setBillingAmount(long billingAmount) {
		this.billingAmount = billingAmount;
	}

	public final long getBillingAmount() {
		return this.billingAmount;
	}

	protected final void setIsBillPaid(boolean isBillPaid) {

		this.isBillPaid = isBillPaid;
	}

	public final boolean isBillPaid() {
		return this.isBillPaid;
	}

	/**
	 * Sets the exit time for a Car on the {@link ParkingTicket} and returns the
	 * duration car was parked. If the exit time on the ticket is already
	 * registered, returns the minutes parked based on the save exit time.
	 */
	protected final Duration setExitDateTime() {
		if (this.exitDateTime == null) {
			this.exitDateTime = DateTime.now();
			this.minutesParked = new Duration(this.entryDateTime, this.exitDateTime);
			return this.minutesParked;
		} else {
			return this.minutesParked;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{id: " + this.id + ", car: " + car.toString() + "entryAt: " + this.entryDateTime.toString()
				+ ", BillingAmount: " + billingAmount + ", hasPaid: " + this.isBillPaid + "}";
	}
}
