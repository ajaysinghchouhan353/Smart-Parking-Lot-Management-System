package org.splms.panels;

import org.splms.ParkingLot;
import org.splms.ParkingSpot;
import org.splms.displayPanel.ExitDisplayPanel;
import org.splms.parkingticket.ParkingTicket;
import org.splms.payment.PaymentProcessor;
import org.splms.coststrategy.CostComputationStrategy;


public class ExitPanel {
    private final ExitDisplayPanel displayPanel;
    private final PaymentProcessor paymentProcessor;
    private final CostComputationStrategy costStrategy;

    public ExitPanel(PaymentProcessor paymentProcessor, CostComputationStrategy costStrategy) {
        this.displayPanel = new ExitDisplayPanel();
        this.paymentProcessor = paymentProcessor;
        this.costStrategy = costStrategy;
    }

    public void unparkVehicle(ParkingTicket ticket, ParkingLot parkingLot) {
        if (ticket == null || ticket.getSpotId() == null) {
            displayPanel.displayError("Invalid parking ticket");
            return;
        }

        double cost = costStrategy.computeCost(ticket);

        ParkingSpot spot = parkingLot.getSpotById(ticket.getSpotId());
        if (spot != null) {
            spot.removeVehicle();
        } else {
            displayPanel.displayError("Parking spot not found: " + ticket.getSpotId());
            return;
        }

        boolean paymentSuccess = paymentProcessor.processPayment(cost);
        
        if (paymentSuccess) {
            displayPanel.displayCost(ticket.getVehicle(), cost);
            displayPanel.displayExitSuccess(ticket.getVehicle());
        } else {
            displayPanel.displayError("Payment failed. Please try again.");
        }
    }
}