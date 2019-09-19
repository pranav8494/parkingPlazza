package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Class for Parking slot for Electric 20KW cars.  Use {@link ParkingSlotFactory} for instantiation.
 * 
 * @author ppandey
 */
public class ParkingForElectric20KW extends ParkingSlot{
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	protected ParkingForElectric20KW() {
		super(CarTypeEnum.ELECTRIC_20KW);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	protected ParkingForElectric20KW(String id) {
		super(id, CarTypeEnum.ELECTRIC_20KW);
	}

}
