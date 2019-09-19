package com.pp.bom.pricingPolicy;

import com.pp.bom.vehicle.Car;

public interface ParkingRate {

	public enum RatePolicy{
		HOURLY_ONLY, HOURLY_WITH_FIXED
	}
	
	/**
	 * Compute the changes for the parking as per the policy and return the amount.
	 * @param vehicle
	 * @param minutes
	 * @return
	 */
	public long computeCharges(Car vehicle, long minutes);
	
	/**
	 * Which {@link RatePolicy} is being used.
	 * @return
	 */
	public String getRatePolicy();
	
	/**
	 * Class for Hourly Parking Rate
	 * @author ppandey 
	 *
	 */
	public class HourlyParkingRate implements ParkingRate {

		private long hourlyFee = 0;
		private RatePolicy ratePolicy = RatePolicy.HOURLY_ONLY;
		protected HourlyParkingRate(long hourlyFee) {

			this.hourlyFee = hourlyFee;
		}
		
		@Override
		public final long computeCharges(Car vehicle, long minutes) {
			return (minutes/60) * hourlyFee;
		}
		
		/**
		 * @return the hourlyFee
		 */
		public final long getHourlyFee() {
			return hourlyFee;
		}

		@Override
		public final String getRatePolicy(){
			return this.ratePolicy.name();
		}	
	}
	
	/**
	 * Class for Hourly With Fixed Parking Rate
	 * @author ppandey 
	 *
	 */
	public class HourlyWithFixedParkingRate implements ParkingRate {

		private long hourlyFee = 0;
		private long fixedFee = 0;
		private RatePolicy ratePolicy = RatePolicy.HOURLY_ONLY;
		protected HourlyWithFixedParkingRate(long hourlyFee, long fixedFee) {

			this.hourlyFee = hourlyFee;
			this.fixedFee = fixedFee;
		}
		
		@Override
		public final long computeCharges(Car vehicle, long minutes) {
			return fixedFee + (minutes/60) * hourlyFee;
		}

		/**
		 * @return the hourlyFee
		 */
		public final long getHourlyFee() {
			return hourlyFee;
		}

		/**
		 * @return the fixedFee
		 */
		public final long getFixedFee() {
			return fixedFee;
		}

		@Override
		public final String getRatePolicy(){
			return this.ratePolicy.name();
		}	
	}
}
