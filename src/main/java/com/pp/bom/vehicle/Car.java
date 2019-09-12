package com.pp.bom.vehicle;

import com.pp.Utils;

/**
 * Class to represent any vehicle of type CAR.
 * @author ppandey
 *
 */
public abstract class Car {

	public enum CarType{
		GASOLINE, ELECTRIC_20KW, ELECTRIC_50KW
	}
	
	private final String id;
	private final CarType type;

	/**
	 * Constructor
	 * @param id Registration number for the car. Can't be Null.
	 * @param type {@link CarType}.
	 */
	public Car(String id, CarType type){
		
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
	public CarType getType(){
		return this.type;
	}
	
}
