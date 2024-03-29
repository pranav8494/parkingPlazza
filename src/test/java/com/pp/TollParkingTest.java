package com.pp;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.pp.bom.Address;
import com.pp.bom.CarTypeEnum;
import com.pp.bom.ParkingTicket;
import com.pp.bom.TollParking;
import com.pp.bom.pricingPolicy.ParkingRate;
import com.pp.bom.pricingPolicy.ParkingRateFactory;
import com.pp.bom.pricingPolicy.ParkingRate.RatePolicy;
import com.pp.bom.vehicle.Car;

public class TollParkingTest {

	private static final Logger LOG = LoggerFactory.getLogger(TollParkingTest.class);
	TollParking parking;
	Car g1, g2, g3, g4, e20k1, e20k2, e20k3, e50k1, e50k2;
	@Before
	public void setUp() throws Exception {
		
    	Address lotAddress = new Address("Route de Nice", "Antibes", "06600", "FR");
    	HashMap<CarTypeEnum, Integer> slotCapacityConfigMap = new HashMap<CarTypeEnum, Integer>();
    	slotCapacityConfigMap.put(CarTypeEnum.GASOLINE, 4);
    	slotCapacityConfigMap.put(CarTypeEnum.ELECTRIC_20KW, 2);
    	slotCapacityConfigMap.put(CarTypeEnum.ELECTRIC_50KW, 1);
    	
    	ParkingRateFactory rateFactory = new ParkingRateFactory();    	
    	ParkingRate rate = rateFactory.getParkingPolicy(RatePolicy.HOURLY_WITH_FIXED, 1, Optional.fromNullable(new Long(20)));
    	
    	this.parking = new TollParking("Hello Parking LOT", lotAddress, slotCapacityConfigMap, rate);
    	
    	g1  = new Car("g1", CarTypeEnum.GASOLINE);
    	g2  = new Car("g2", CarTypeEnum.GASOLINE);
    	g3  = new Car("g3", CarTypeEnum.GASOLINE);
    	g4  = new Car("g4", CarTypeEnum.GASOLINE);
    	e20k1  = new Car("e20k1", CarTypeEnum.ELECTRIC_20KW);
    	e20k2  = new Car("e20k2", CarTypeEnum.ELECTRIC_20KW);
    	e20k3  = new Car("e20k3", CarTypeEnum.ELECTRIC_20KW);
    	e50k1  = new Car("e50k1", CarTypeEnum.ELECTRIC_50KW);
    	e50k2  = new Car("e50k2", CarTypeEnum.ELECTRIC_50KW);
	}

	@Test
	public void testParking() {
		// RequestParking

		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(g1).isPresent());
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(g2).isPresent());
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(g3).isPresent());
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(g4).isPresent());
		Optional<ParkingTicket> ticketE30K1 = this.parking.requestParking(e20k1);
		Assert.assertTrue("Should have got a parking.", ticketE30K1.isPresent());
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(e20k2).isPresent());
		Assert.assertTrue("Should not have got a parking.", !this.parking.requestParking(e20k3).isPresent());
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(e50k1).isPresent());
		Assert.assertTrue("Should not have got a parking.", !this.parking.requestParking(e50k2).isPresent());
		
		// Exiting parking
		Optional<String> e50k1TicketId = this.parking.exitParking(e50k1);
		Assert.assertTrue("Bill should be there.", e50k1TicketId.isPresent());
		if(e50k1TicketId.isPresent()){
			LOG.info("Bill ID: " + e50k1TicketId.get());
		}
		
		// Exit a car which is not in parking
		Optional<String> e50k2TicketId = this.parking.exitParking(e50k2);
		Assert.assertTrue("Bill should not be there.", !e50k2TicketId.isPresent());
		
		
		// Request next slot after last car exit
		Assert.assertTrue("Should have got a parking.", this.parking.requestParking(e50k2).isPresent());
		
		// Car with empty id
		try {
			new Car("", CarTypeEnum.GASOLINE);
			Assert.assertTrue("Should have thrown an IllegalArgumentException.", false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue("Should have thrown an IllegalArgumentException.", false);
		}
		
		// Request for slot for a Car already assigned a slot
		Optional<ParkingTicket> ticket = this.parking.requestParking(e20k1);
		Assert.assertTrue(ticket.isPresent() && ticket.get().getId().equals(ticketE30K1.get().getId()));
		
		LOG.info("All Unpaid bills: {}" , parking.getAllUnpaidBillDetail());
	}

}
