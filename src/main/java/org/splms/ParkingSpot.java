package org.splms;

import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
    private final String id;
    private final SpotType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingSpot(String id, SpotType spotType) {
        this.id = id;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
      if (isOccupied) {
        return false;
      }

        return switch (vehicle.getType()) {
            case CAR -> spotType == SpotType.SMALL || spotType == SpotType.MEDIUM;
            case BUS, TRUCK -> spotType == SpotType.LARGE;
        };
    }
    
     public void parkVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
        } finally {
            lock.unlock();
        }
     }

     public void removeVehicle() {
        lock.lock();
        try {
            this.parkedVehicle = null;
            this.isOccupied = false;
        } finally {
            lock.unlock();
        }
     }

     public boolean isOccupied() {
         return isOccupied;
     }

     public String getId() {
         return id;
     }

     public SpotType getSpotType() {
         return spotType;
     }

     public Vehicle getParkedVehicle() {
         return parkedVehicle;
     }
}
