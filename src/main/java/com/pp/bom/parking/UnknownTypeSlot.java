package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

public class UnknownTypeSlot extends ParkingSlot {

	/**
	 * Constructor
	 * @param slotType
	 */
	protected UnknownTypeSlot() {
		super(CarTypeEnum.UNKNOWN);
	}
}
