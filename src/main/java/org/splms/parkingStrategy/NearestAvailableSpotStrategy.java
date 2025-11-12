package org.splms.parkingStrategy;

import java.util.List;
import org.splms.ParkingLot;
import org.splms.parkingFloor.ParkingFloor;
import org.splms.ParkingSpot;
import org.splms.Vehicle;


public class NearestAvailableSpotStrategy implements ParkingStrategy {
  @Override
  public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot) {
    for (ParkingFloor floor : parkingLot.getFloors()) {
      if (floor.isUnderMaintenance()) continue;
      ParkingSpot spot = floor.getAvailableSpot(vehicle);
      if (spot != null) return spot;
    }
    return null;
  }
}