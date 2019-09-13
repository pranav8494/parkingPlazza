package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Class to instantiate Parking slot for Electric 50KW cars.
 * 
 * @author ppandey
 */
public class ParkingForElectric50KW extends ParkingSlot {

	/**
		 * Constructor.
		 * 
		 * @param id
		 */
		public ParkingForElectric50KW(String id) {
			super(id, CarTypeEnum.ELECTRIC_50KW);
		}
}
