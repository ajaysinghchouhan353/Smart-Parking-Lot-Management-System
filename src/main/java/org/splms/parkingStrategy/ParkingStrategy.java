package org.splms.parkingStrategy;

import org.splms.ParkingLot;
import org.splms.ParkingSpot;
import org.splms.Vehicle;


public interface ParkingStrategy {
    ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot);
}