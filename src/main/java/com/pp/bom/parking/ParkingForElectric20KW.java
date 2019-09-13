package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Class to instantiate Parking slot for Electric 20KW cars.
 * 
 * @author ppandey
 */
public class ParkingForElectric20KW extends ParkingSlot{
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public ParkingForElectric20KW(String id) {
		super(id, CarTypeEnum.ELECTRIC_20KW);
	}

}
