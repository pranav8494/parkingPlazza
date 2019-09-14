package com.pp;

import com.pp.bom.CarTypeEnum;
import com.pp.bom.pricingPolicy.ParkingRate;
import com.pp.bom.vehicle.Car;

public class MinuteBasedPricing implements ParkingRate {

	long gasCarPerMinCost = 0;
	long eCarPerMinCost = 0;
	public MinuteBasedPricing(long gasCarPerMinCost, long eCarPerMinCost) {
		this.gasCarPerMinCost = gasCarPerMinCost;
		this.eCarPerMinCost = eCarPerMinCost;
	}
	@Override
	public long computeCharges(Car vehicle, long minutes) {
		
		if(CarTypeEnum.GASOLINE.equals(vehicle.getType())){
			return minutes * this.gasCarPerMinCost;
		}
		else{
			return minutes * this.eCarPerMinCost;
		}
	}

	@Override
	public String getRatePolicy() {
		return "MINUTE_BASED";
	}

}
