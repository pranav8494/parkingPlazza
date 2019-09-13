package com.pp;

import java.util.ArrayList;
import java.util.List;
import com.pp.bom.vehicle.Car;

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

}
