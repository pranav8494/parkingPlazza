/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.pp;

import com.pp.bom.pricingPolicy.ParkingRate;

import java.util.HashMap;

import com.pp.bom.*;

public class App {

    public static void main(String[] args) {
    	
    	Address lotAddress = new Address("Route de Nice", "Antibes", "06600", "FR");
    	HashMap<CarTypeEnum, Integer> slotCapacity = new HashMap<CarTypeEnum, Integer>();
    	slotCapacity.put(CarTypeEnum.GASOLINE, 4);
    	slotCapacity.put(CarTypeEnum.ELECTRIC_20KW, 2);
    	slotCapacity.put(CarTypeEnum.ELECTRIC_50KW, 1);
    	
    	// Do Something here to use parking.
    }
}
