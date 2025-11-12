package org.splms.parkingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.splms.ParkingLot;
import org.splms.parkingFloor.ParkingFloor;
import org.splms.ParkingSpot;
import org.splms.Vehicle;


public class RandomSpotStrategy implements ParkingStrategy {
  @Override
  public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot) {
    List<ParkingFloor> floors = new ArrayList<>(parkingLot.getFloors());
    Collections.shuffle(floors);
    for (ParkingFloor floor : floors) {
      if (floor.isUnderMaintenance()) continue;

      ParkingSpot spot = floor.getAvailableSpot(vehicle);
      if (spot != null) return spot;
    }
    return null;
  }
}