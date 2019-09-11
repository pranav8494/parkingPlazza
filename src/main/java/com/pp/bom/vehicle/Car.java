package com.pp.bom.vehicle;

import com.pp.Utils;

/**
 * Class to represent any vehicle of type CAR.
 * @author ppandey
 *
 */
public abstract class Car {

	private final String id;
	private final String type;
	
	/**
	 * Constructor
	 * @param id
	 * @param type
	 */
	public Car(String id, String type){
		
		Utils.checkStringEmptyOrNull(id);
		this.id = id; 
		
		Utils.checkStringEmptyOrNull(type);
		if(!Utils.isValidCarType(type)){
			throw new IllegalArgumentException("Type is not valid");
		}
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
	public String getType(){
		return this.type;
	}
	
	/**
	 * Defining necessary values for objects of this class to choose from for Developer usage.
	 * @author ppandey
	 *
	 */
	public static final class Catalog{
		public static String GASOLINE = "gasoline";
		public static String ELECTRIC_20KW = "electic_20kw";
		public static String ELECTRIC_50KW = "electic_50kw";
	}
}
