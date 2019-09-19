package parking;

import org.junit.Assert;
import org.junit.Test;

import com.pp.bom.CarTypeEnum;
import com.pp.bom.parking.ParkingForElectric20KW;
import com.pp.bom.parking.ParkingForElectric50KW;
import com.pp.bom.parking.ParkingForGasolineCar;
import com.pp.bom.parking.ParkingSlot;
import com.pp.bom.parking.ParkingSlotFactory;

public class ParkingSlotFactroyTest {
	
	@Test
	public void parkingInstationTest(){
		ParkingSlotFactory factory = new ParkingSlotFactory();
		
		ParkingSlot gasSlot = factory.createParkingSlot(CarTypeEnum.GASOLINE);
		ParkingSlot e20KSlot = factory.createParkingSlot(CarTypeEnum.ELECTRIC_20KW);
		ParkingSlot e50KSlot = factory.createParkingSlot(CarTypeEnum.ELECTRIC_50KW);
		
		Assert.assertTrue("Should be Gasoline", gasSlot instanceof ParkingForGasolineCar && gasSlot.getSlotType().equals(CarTypeEnum.GASOLINE));
		Assert.assertTrue("Should be Electric20K", e20KSlot instanceof ParkingForElectric20KW && e20KSlot.getSlotType().equals(CarTypeEnum.ELECTRIC_20KW));
		Assert.assertTrue("Should be Electric50K", e50KSlot instanceof ParkingForElectric50KW && e50KSlot.getSlotType().equals(CarTypeEnum.ELECTRIC_50KW));
	}

}
