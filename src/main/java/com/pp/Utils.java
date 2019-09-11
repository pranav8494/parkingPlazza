package com.pp;

import java.util.ArrayList;
import java.util.List;import com.pp.bom.vehicle.Car;

public class Utils {
	
	/**
	 * Checks if the given {@link String} value is empty or Null, if true, throws an {@link IllegalArgumentException}.
	 * @param value
	 */
	public static void checkStringEmptyOrNull(String value){
		if(value == null || value.trim().isEmpty()){
			throw new IllegalArgumentException("Value cannot be NULL or EMPTY");
		}
	}
	
	/**
	 * Get all available car types in system. Change this if need this to be dynamic.
	 * @return 
	 */
	public static List<String> getAllCarTypes(){
		List<String> types = new ArrayList<String>();
		types.add(Car.Catalog.GASOLINE);
		types.add(Car.Catalog.ELECTRIC_20KW);
		types.add(Car.Catalog.ELECTRIC_50KW);
		
		return types;
	}
	
	/**
	 * Checks for if the given type is a valid type of car available in system.
	 * @param type
	 * @return
	 */
	public static boolean isValidCarType(String type){
		
		return getAllCarTypes().contains(type);
	}

}
