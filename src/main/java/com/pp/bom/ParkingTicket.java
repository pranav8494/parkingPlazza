package com.pp.bom;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.pp.bom.parking.ParkingSlot;
import com.pp.bom.vehicle.Car;

/**
 * Class for ParkingTicket.
 *
 * </br>
 * The contructor is protected as we don't want anyone else except the
 * TollParking to create a parking tickets. Also all the seters are kept
 * protected to limit the usage out of the package
 * 
 * @author ppandey
 *
 */
public class ParkingTicket {

	private final String id;
	private final Car car;
	private final ParkingSlot slot;
	private final DateTime entryDateTime;
	private DateTime exitDateTime = null;
	private Duration minutesParked = Duration.ZERO;
	private long billingAmount = 0;
	private boolean isBillPaid = false;

	protected ParkingTicket(Car car, ParkingSlot slot) {
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

	/**
	 * Gets the billing amount calculated based on number of minutes the vehicle was parked.
	 * @return
	 */
	public final long getBillingAmount() {
		return this.billingAmount;
	}

	/**
	 * The set billPayment flag to <code>true</code>
	 * @param isBillPaid
	 */
	protected final void setIsBillPaid(boolean isBillPaid) {

		this.isBillPaid = isBillPaid;
	}

	/**
	 * Returns <code>true</code> or <code>false</code> based on the bill payment status.
	 * @return
	 */
	public final boolean isBillPaid() {
		return this.isBillPaid;
	}

	/**
	 * Sets the exit time for a Car on the {@link ParkingTicket} and returns the
	 * duration car was parked. </br>
	 * If the exit time on the ticket is already registered, returns the minutes
	 * parked based on the save exit time.
	 * 
	 * @return {@link Duration} between entry and exit time. if the the vehicle has not exited yet, returns ZERO.
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
