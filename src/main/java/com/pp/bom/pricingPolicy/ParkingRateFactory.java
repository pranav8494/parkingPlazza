package com.pp.bom.pricingPolicy;

import com.google.common.base.Optional;
import com.pp.bom.pricingPolicy.ParkingRate.HourlyParkingRate;
import com.pp.bom.pricingPolicy.ParkingRate.HourlyWithFixedParkingRate;
import com.pp.bom.pricingPolicy.ParkingRate.RatePolicy;

/**
 * Factory for {@link ParkingRate}.
 * @author ppandey
 *
 */
public class ParkingRateFactory {

	/**
	 * Gets {@link ParkingRate} based on given parameters (eithe
	 * {@link HourlyParkingRate} or {@link HourlyWithFixedParkingRate}. </br>
	 * If fixed rate is {@link Optional} absent, then returns
	 * {@link HourlyWithFixedParkingRate} with fixed rate set to 0.
	 * 
	 * @param policy
	 *            {@link RatePolicy}
	 * @param hourlyFee
	 * @param fixedFee
	 * @return
	 */
	public ParkingRate getParkingPolicy(RatePolicy policy, long hourlyFee, Optional<Long> fixedFee) {

		switch (policy) {
		case HOURLY_ONLY:
			return new ParkingRate.HourlyParkingRate(hourlyFee);
		case HOURLY_WITH_FIXED:
			if (fixedFee.isPresent())
				return new ParkingRate.HourlyWithFixedParkingRate(hourlyFee, fixedFee.get());
			else
				return new ParkingRate.HourlyWithFixedParkingRate(hourlyFee, 0);
		default:
			return null;
		}
	}

	/**
	 * Get {@link ParkingRate} with an hourly fee.
	 * @param hourlyFee
	 * @return
	 */
	public ParkingRate getHourlyParking(long hourlyFee) {
		return getParkingPolicy(RatePolicy.HOURLY_ONLY, hourlyFee, Optional.absent());
	}

	/**
	 * Gets a {@link ParkingRate} with hourly fee and fixed fee.
	 * @param hourlyFee
	 * @param fixedFee
	 * @return
	 */
	public ParkingRate getHourlyWithFixedParking(long hourlyFee, long fixedFee) {
		return getParkingPolicy(RatePolicy.HOURLY_ONLY, hourlyFee, Optional.fromNullable(fixedFee));
	}
}
