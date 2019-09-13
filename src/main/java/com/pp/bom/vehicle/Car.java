package com.pp.bom.vehicle;

import com.pp.Utils;
import com.pp.bom.CarTypeEnum;

/**
 * Class to represent any vehicle of type CAR.
 * @author ppandey
 *
 */
public class Car {
	
	private final String id;
	private final CarTypeEnum type;

	/**
	 * Constructor
	 * @param id Registration number for the car. Can't be Null.
	 * @param type {@link CarType}.
	 */
	public Car(String id, CarTypeEnum type){
		
		Utils.checkStringEmptyOrNull(id);
		this.id = id; 		
		this.type = type; 
	}
	
	/**
	 * Get the Registration ID of the vehicle.
	 * @return
	 */
	public String getRegistrationId(){
		return this.id;
	}
	
	/**
	 * Gets the type of vehicle.
	 * @return
	 */
	public CarTypeEnum getType(){
		return this.type;
	}
	
}
