package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Class for Parking slot for Electric 50KW cars. Use {@link ParkingSlotFactory} for instantiation.
 * 
 * @author ppandey
 */
public class ParkingForElectric50KW extends ParkingSlot {

	/**
	 * Constructor.
	 * 
	 */
	protected ParkingForElectric50KW() {
		super(CarTypeEnum.ELECTRIC_50KW);
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	protected ParkingForElectric50KW(String id) {
		super(id, CarTypeEnum.ELECTRIC_50KW);
	}
}
