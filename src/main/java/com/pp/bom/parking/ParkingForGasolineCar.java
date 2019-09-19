package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/** 
 * Class for Parking slot for Gasoline cars. Use {@link ParkingSlotFactory} for instantiation.
 * 
 * @author ppandey
 */
public class ParkingForGasolineCar extends ParkingSlot {

	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	protected ParkingForGasolineCar(String id) {
		super(id, CarTypeEnum.GASOLINE);
	}
	
	protected ParkingForGasolineCar() {
		super(CarTypeEnum.GASOLINE);
	}
}
