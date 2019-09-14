# parkingPlazza
A Java API for parking plazza.


## Setup the Dev env.

The project is built with Gradle. 

### Setup eclipse

Simply checkout the project and from bash run following to setup dev env. for eclipse:

```./gradlew eclipse```

### List all build tasks:

```./gradlew tasks```

### Package the jar:

```./gradlew fatJar```

After the above command, the jar will be placed under `build\libs`.

## API:

* Start by creating an instance of ```TollParking``` to setup the parking plazza.
``` TollParking parkingLot = new TollParking("Hello Parking LOT", lotAddress, slotCapacity, rate);``` 

* ```TollParking``` exposes APIs t operate the parking plazza.
  * ```parking.reqestParking(car)```: To request a parking slot for the given car.
  * ```parking.exitParking(Car)```: exit the parking, This will return a ticketID which can be used to access the parking tickets, amount to be paid, record payment.
  * ```parking.getBillingAmount(ticketId)```
  * ```parking.isBillPaid(ticketId)```
  * ```parking.payBill(ticketId)```: Records that payment has been done for given bill ID.
  * ```parking.getAllUnPaidBill()```: Get a table with ticketId, car ID and billing amount.
  
  Refer to [TollParkingTest.java](https://github.com/pranav8494/parkingPlazza/blob/master/src/test/java/com/pp/TollParkingTest.java) for example code.

