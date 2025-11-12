package org.splms.coststrategy;

import org.splms.SpotType;
import org.splms.VehicleType;
import org.splms.parkingticket.ParkingTicket;

public class StandardCostComputationStrategy implements CostComputationStrategy {
    
    private static final double CAR_BASE_RATE = 10.0;
    private static final double BUS_BASE_RATE = 25.0;
    private static final double TRUCK_BASE_RATE = 20.0;
    
    private static final double SMALL_SPOT_MULTIPLIER = 1.0;
    private static final double MEDIUM_SPOT_MULTIPLIER = 1.3;
    private static final double LARGE_SPOT_MULTIPLIER = 1.5;
    
    private static final double MINIMUM_HOURS = 0.5;
    
    @Override
    public double computeCost(ParkingTicket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Parking ticket cannot be null");
        }
        
        long durationMillis = System.currentTimeMillis() - ticket.getEntryTime();
        double durationHours = Math.max(durationMillis / (1000.0 * 60 * 60), MINIMUM_HOURS);
        
        double baseRate = getBaseRateForVehicle(ticket.getVehicle().getType());
        
        double spotMultiplier = getSpotMultiplier(ticket.getSpotType());
        
        double totalCost = durationHours * baseRate * spotMultiplier;
        
        return Math.round(totalCost * 100.0) / 100.0;
    }
    
    private double getBaseRateForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return CAR_BASE_RATE;
            case BUS:
                return BUS_BASE_RATE;
            case TRUCK:
                return TRUCK_BASE_RATE;
            default:
                return CAR_BASE_RATE;
        }
    }
    
    private double getSpotMultiplier(String spotType) {
        try {
            SpotType type = SpotType.valueOf(spotType.toUpperCase());
            switch (type) {
                case SMALL:
                    return SMALL_SPOT_MULTIPLIER;
                case MEDIUM:
                    return MEDIUM_SPOT_MULTIPLIER;
                case LARGE:
                    return LARGE_SPOT_MULTIPLIER;
                default:
                    return SMALL_SPOT_MULTIPLIER;
            }
        } catch (IllegalArgumentException e) {
            return SMALL_SPOT_MULTIPLIER;
        }
    }
}