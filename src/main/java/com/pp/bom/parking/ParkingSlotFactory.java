package com.pp.bom.parking;

import com.pp.bom.CarTypeEnum;

/**
 * Factory to instantiate {@link ParkingSlot}.
 * @author ppandey
 *
 */
public class ParkingSlotFactory {

	/**
	 * Created and returns a {@link ParkingSlot} given the {@link CarTypeEnum}.
	 * @param type
	 * @return
	 */
	public final ParkingSlot createParkingSlot(CarTypeEnum type){
		
		switch (type) {
		case GASOLINE:
			return new ParkingForGasolineCar();
		case ELECTRIC_20KW:
			return new ParkingForElectric20KW();
		case ELECTRIC_50KW:
			return new ParkingForElectric50KW();
		default:
			return new UnknownTypeSlot();
		}
	}
}
