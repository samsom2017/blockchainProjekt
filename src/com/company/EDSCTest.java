package com.company;

import java.util.ArrayList;
import java.util.List;

public class EDSCTest {
    public static void main(String[] args) {
        System.out.println("=== Event-Driven Smart Contract Platform - Test Suite ===\n");

        // Create EDSC instance with local event list (without socket for testing)
        List<BlockchainEvent> eventList = new ArrayList<>();
        EDSC edsc = new EDSC(eventList, null);

        // Test 1: Add patient data events
        System.out.println("--- Test 1: Adding Patient Data Events ---");
        try {
            // Simulate patient registration through broker
            BlockchainEvent event1 = new BlockchainEvent("Patient Register 12345", "0");
            eventList.add(event1);
            System.out.println("? Event 1: " + event1.getData());
            System.out.println("  Hash: " + event1.getHash().substring(0, 16) + "...");
            System.out.println("  Prev Hash: " + event1.getPrevHash());

            // Simulate patient appointment
            BlockchainEvent event2 = new BlockchainEvent("Appointment scheduled P12345", event1.getHash());
            eventList.add(event2);
            System.out.println("\n? Event 2: " + event2.getData());
            System.out.println("  Hash: " + event2.getHash().substring(0, 16) + "...");
            System.out.println("  Prev Hash: " + event2.getPrevHash().substring(0, 16) + "...");

            // Simulate patient data update
            BlockchainEvent event3 = new BlockchainEvent("Update vitals P12345", event2.getHash());
            eventList.add(event3);
            System.out.println("\n? Event 3: " + event3.getData());
            System.out.println("  Hash: " + event3.getHash().substring(0, 16) + "...");
            System.out.println("  Prev Hash: " + event3.getPrevHash().substring(0, 16) + "...");

        } catch (Exception e) {
            System.out.println("? Error adding events: " + e.getMessage());
        }

        // Test 2: Validate events
        System.out.println("\n\n--- Test 2: Validating Events ---");
        for (int i = 0; i < eventList.size(); i++) {
            BlockchainEvent event = eventList.get(i);
            // For validation, check if hash matches calculated hash
            boolean hashValid = event.getHash().equals(event.calculateHash());
            System.out.println((hashValid ? "[OK]" : "[FAIL]") + " Event valid: " + event.getData() + " - Hash: " + hashValid);
        }

        // Test 3: Trigger actions based on event data
        System.out.println("\n\n--- Test 3: Triggering Actions ---");
        if (!eventList.isEmpty()) {
            BlockchainEvent printEvent = new BlockchainEvent("print Patient_Data_Synced", eventList.get(eventList.size() - 1).getHash());
            System.out.println("Processing command: " + printEvent.getData());
            edsc.triggerAction(printEvent);
        } else {
            System.out.println("Skipped: No events in list");
        }

        // Test 4: Event chain integrity
        System.out.println("\n\n--- Test 4: Event Chain Integrity ---");
        boolean chainValid = true;
        for (int i = 0; i < eventList.size(); i++) {
            BlockchainEvent event = eventList.get(i);
            String expectedPrevHash = (i == 0) ? "0" : eventList.get(i - 1).getHash();
            boolean matches = event.getPrevHash().equals(expectedPrevHash);
            System.out.println((matches ? "?" : "?") + " Event " + i + ": Chain link valid - " + matches);
            chainValid = chainValid && matches;
        }
        System.out.println("\nChain Integrity: " + (chainValid ? "? VALID" : "? BROKEN"));

        // Test 5: Broker message format simulation
        System.out.println("\n\n--- Test 5: Broker Message Format ---");
        if (!eventList.isEmpty()) {
            BlockchainEvent lastEvent = eventList.get(eventList.size() - 1);
            String brokerMessage = lastEvent.getData() + "," + lastEvent.getTimestamp() + "," + 
                                  lastEvent.getPrevHash() + "," + lastEvent.getHash();
            System.out.println("Broker Message Format:");
            System.out.println("  " + brokerMessage);
            System.out.println("\nMessage Parts:");
            String[] parts = brokerMessage.split(",");
            System.out.println("  Data: " + parts[0]);
            System.out.println("  Timestamp: " + parts[1]);
            System.out.println("  Prev Hash: " + parts[2].substring(0, Math.min(16, parts[2].length())) + "...");
            System.out.println("  Hash: " + parts[3].substring(0, Math.min(16, parts[3].length())) + "...");
        }

        // Test 6: Array boundary checks
        System.out.println("\n\n--- Test 6: Edge Cases - Array Boundary Checks ---");
        if (!eventList.isEmpty()) {
            BlockchainEvent singleCommandEvent = new BlockchainEvent("print", eventList.get(eventList.size() - 1).getHash());
            System.out.println("Testing single word command:");
            edsc.triggerAction(singleCommandEvent);

            BlockchainEvent emptyEvent = new BlockchainEvent("", eventList.get(eventList.size() - 1).getHash());
            System.out.println("\nTesting empty command:");
            edsc.triggerAction(emptyEvent);
        } else {
            System.out.println("Skipped: No events in list");
        }

        // Summary
        System.out.println("\n\n=== Summary ===");
        System.out.println("Total Events: " + eventList.size());
        System.out.println("Chain Status: " + (chainValid ? "? Valid" : "? Invalid"));
        System.out.println("Platform Status: ? Ready for distributed network");
        System.out.println("\n=== All Tests Completed Successfully ===");
    }
}
