package org.splms.parkingticket;

import org.splms.ParkingSpot;
import org.splms.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingTicketGenerator {
    private static final AtomicInteger ticketCounter = new AtomicInteger(0);
    private static final String TICKET_PREFIX = "PT";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");


    public ParkingTicket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot cannot be null");
        }

        String ticketId = generateTicketId();
        return new ParkingTicket(
            ticketId, 
            vehicle, 
            parkingSpot.getId(), 
            parkingSpot.getSpotType().name()
        );
    }
    private String generateTicketId() {
        String dateStr = LocalDateTime.now().format(DATE_FORMAT);
        int counter = ticketCounter.incrementAndGet();
        return String.format("%s-%s-%04d", TICKET_PREFIX, dateStr, counter);
    }

    public void resetCounter() {
        ticketCounter.set(0);
    }

    public int getCurrentCounter() {
        return ticketCounter.get();
    }
}