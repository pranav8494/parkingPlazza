package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Class to instantiate Parking slot for Gasoline cars.
 * 
 * @author ppandey
 */
public class ParkingForGasolineCar extends ParkingSlot {

	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public ParkingForGasolineCar(String id) {
		super(id, CarTypeEnum.GASOLINE);
	}
}
