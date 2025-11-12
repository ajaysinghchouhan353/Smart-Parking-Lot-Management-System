package org.splms.panels;

import org.splms.ParkingLot;
import org.splms.ParkingSpot;
import org.splms.Vehicle;
import org.splms.displayPanel.EntryDisplayPanel;
import org.splms.parkingStrategy.ParkingStrategy;
import org.splms.parkingticket.ParkingTicket;
import org.splms.parkingticket.ParkingTicketGenerator;


public class EntryPanel {
    private ParkingStrategy strategy;
    private final EntryDisplayPanel displayPanel;
    private final ParkingTicketGenerator ticketGenerator;

    public EntryPanel(ParkingStrategy strategy) {
        this(strategy, new ParkingTicketGenerator());
    }

    public EntryPanel(ParkingStrategy strategy, ParkingTicketGenerator ticketGenerator) {
        this.strategy = strategy;
        this.displayPanel = new EntryDisplayPanel();
        this.ticketGenerator = ticketGenerator;
    }

    public void changeStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, ParkingLot parkingLot) {
        ParkingSpot spot = strategy.findSpot(vehicle, parkingLot);
        ParkingTicket ticket = null;
        if (spot != null) {
            ticket = ticketGenerator.generateTicket(vehicle, spot);
            spot.parkVehicle(vehicle);
            parkingLot.issueTicket(ticket);
        }
        displayPanel.displayTicketIssued(ticket);
        return ticket;
    }
}