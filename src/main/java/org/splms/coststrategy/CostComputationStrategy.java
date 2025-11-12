package org.splms.coststrategy;

import org.splms.parkingticket.ParkingTicket;

public interface CostComputationStrategy {

    double computeCost(ParkingTicket ticket);
}
