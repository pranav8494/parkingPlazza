package com.pp.bom;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.pp.bom.parking.ParkingSlot.SlotType;

public class TollParking {
	
	protected static final DateTimeFormatter                LOCAL_DATE_FORMAT = DateTimeFormat.forPattern("yyyyMMdd");
	  
	private static final Logger                             LOG               = LoggerFactory.getLogger(TollParking.class);
	
	private final Address tollParkingAddress;
	private final String tollParkingName;
	
	private Map<SlotType, Integer> parkingSlotCounter = new HashMap<SlotType, Integer>();
	private ImmutableMap<SlotType, Integer> maxSlotCapavity;
	
	private TollParking(String tollParkingName, Address tollParkingAddress, HashMap<SlotType, Integer> maxSlotCapacity){
		
		this.tollParkingName = tollParkingName;
		this.tollParkingAddress = tollParkingAddress;
		parkingSlotCounter.putAll(maxSlotCapacity);
		this.maxSlotCapavity = ImmutableMap.copyOf(maxSlotCapacity);
	}
	

}

