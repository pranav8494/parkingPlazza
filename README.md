# Parking Plazza
A Java API for parking plazza.


## Setup the Dev env.

The project is built with Gradle. 

### Setup eclipse

Simply checkout the project and from bash run following gradle command to setup dev env. for eclipse:

```./gradlew eclipse```

Once run, import the project in Eclipse using the "Import Existing Project" option (File -> Import -> Existing Project From Workspace).

### List all build tasks:

```./gradlew tasks```

### Package the jar:

To package and release a jar, run the `fatJar`  gradle task.


```./gradlew fatJar```

With the above command, the jar will be placed under `build\libs`.

## API:

### BOM

The BOM for project was under package `com.pp.bom`.  Following BOM have been added (in alphabetical order): 
* [`Address`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/Address.java): Represnts an address.
* [`Car`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/vehicle/Car.java): Representing a car, which may need a parking slot in the parking plazza. The constructor of `Car` class needs two arguments:
  * `id`: Which can't be emtpy, else throw an `IllegalArgumentException`.
  * `type`: [`CarEnumType`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/CarTypeEnum.java)
	```
	Car car = new Car(id, CarEnumType.GASOLINE);
	```
* [`CarEnumType`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/CarTypeEnum.java): Enums to represent car types.
* [ParkingRate](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/pricingPolicy/ParkingRate.java): An interface to define a pricing policy. Following imlementation exist and others can be added:
 	* HourlyParkingRate
 	* HourlyWithFixedParkingRate
* [ParkingSlot](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/parking/ParkingSlot.java): Represents a Parking slot. They are added when a [`TollParking`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/TollParking.java) object is instantiated. Following extenstions exist and may be added depending on the need:
 	* [`ParkingForGasolineCar`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/parking/ParkingForGasolineCar.java)
 	* [`ParkingForElectric20KW`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/parking/ParkingForElectric20KW.java)
 	* [`ParkingForElectric50KW`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/parking/ParkingForElectric50KW.java)
* [ParkingTicket](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/ParkingTicket.java): This is created everytime a `ParkingSlot` is assigned to a `Car`. The constructor and all the setters are `protected` to protect the instantiation. Each parking ticket gets a unique ID. Following methods are publically accessible:
  * `getId()`
  * `getCar()`
  * `getSlot()` 
  * `getEntryDateTime()`
  * `getExitDateTime()`
  * `getBillingAmount()`
  * `isBillPaid()`
* [`TollParking`](https://github.com/pranav8494/parkingPlazza/blob/master/src/main/java/com/pp/bom/TollParking.java): Contains all the business logic for the operating the parking plazza. To instantiate an object of this class,

```
	Address lotAddress = new Address("Route de Nice", "Antibes", "06600", "FR");
	HashMap<CarTypeEnum, Integer> slotCapacity = new HashMap<CarTypeEnum, Integer>();
	slotCapacity.put(CarTypeEnum.GASOLINE, 4);
	slotCapacity.put(CarTypeEnum.ELECTRIC_20KW, 2);
	slotCapacity.put(CarTypeEnum.ELECTRIC_50KW, 1);

	ParkingRate rate = new ParkingRate.HourlyWithFixedParkingRate(1,20);
	TollParking parking = new TollParking("Hello Parking LOT", address, slotCapacity, rate)
```

* Following methods are exposed through `TollParking`: 
	* ```parking.reqestParking(car)```: To request a parking slot for the given car.
	* ```parking.exitParking(Car)```: exit the parking, This will return a ticketID which can be used to access the parking tickets, amount to be paid, record payment.
	* ```parking.getBillingAmount(ticketId)```
	* ```parking.isBillPaid(ticketId)```: Check is given bill id has been paid or not?
	* ```parking.payBill(ticketId)```: Records that payment has been done for given bill ID.
	* ```parking.getAllUnPaidBill()```: Get a table with ticketId, car ID and billing amount.


  
Refer to [TollParkingTest.java](https://github.com/pranav8494/parkingPlazza/blob/master/src/test/java/com/pp/TollParkingTest.java) for test code.

