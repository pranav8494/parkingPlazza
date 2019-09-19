package com.pp.parkingPolicy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import com.pp.bom.pricingPolicy.ParkingRateFactory;
import com.pp.bom.pricingPolicy.ParkingRate;
import com.pp.bom.pricingPolicy.ParkingRate.HourlyParkingRate;
import com.pp.bom.pricingPolicy.ParkingRate.HourlyWithFixedParkingRate;
import com.pp.bom.pricingPolicy.ParkingRate.RatePolicy;

public class ParkingRateFactoryTest {

	@Test
	public void test() {
		ParkingRateFactory factory = new ParkingRateFactory();
		ParkingRate hourlyParking1 = factory.getHourlyParking(10);
		ParkingRate hourlyWithFixedParking1 = factory.getHourlyWithFixedParking(10, 1);
		ParkingRate hourlyParking2 = factory.getParkingPolicy(RatePolicy.HOURLY_ONLY, 10, Optional.absent());
		ParkingRate hourlyWithFixedParking2 = factory.getParkingPolicy(RatePolicy.HOURLY_ONLY, 10,
				Optional.fromNullable(new Long(1)));

		assertTrue("Shoud've been hourly parking.", hourlyParking1 instanceof HourlyParkingRate
				&& hourlyParking1.getRatePolicy().equals(RatePolicy.HOURLY_ONLY));
		assertTrue("Shoud've been hourly parking.", hourlyParking2 instanceof HourlyParkingRate
				&& hourlyParking2.getRatePolicy().equals(RatePolicy.HOURLY_ONLY));
		assertTrue("Shoud've been hourly parking.", hourlyWithFixedParking1 instanceof HourlyWithFixedParkingRate
				&& hourlyWithFixedParking1.getRatePolicy().equals(RatePolicy.HOURLY_WITH_FIXED));
		assertTrue("Shoud've been hourly parking.", hourlyWithFixedParking2 instanceof HourlyWithFixedParkingRate
				&& hourlyWithFixedParking2.getRatePolicy().equals(RatePolicy.HOURLY_WITH_FIXED));
	}

}
