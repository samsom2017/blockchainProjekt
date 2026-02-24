package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Blockchain Simulation System
 * Provides realistic scenarios for testing the EDSC blockchain
 */
public class BlockchainSimulation {
    private EDSC edsc;
    private List<BlockchainEvent> events;
    private PatientRegistration registration;
    private Scanner scanner;

    public BlockchainSimulation(EDSC edsc, List<BlockchainEvent> events) {
        this.edsc = edsc;
        this.events = events;
        this.registration = new PatientRegistration(edsc, events);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main simulation menu
     */
    public void startSimulation() {
        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           BLOCKCHAIN SIMULATION CENTER");
            System.out.println("=".repeat(60));
            System.out.println("1. Patient Registration System");
            System.out.println("2. Quick Simulation (5 Patients)");
            System.out.println("3. Advanced Simulation (Custom)");
            System.out.println("4. Stress Test (1000 Events)");
            System.out.println("5. Hospital Workflow Scenario");
            System.out.println("6. Data Analysis & Reports");
            System.out.println("7. View Dashboard");
            System.out.println("8. Back to Main Menu");
            System.out.println("=".repeat(60));
            System.out.print("Select option (1-8): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registration.startInteractiveMode();
                    break;
                case "2":
                    quickSimulation();
                    break;
                case "3":
                    advancedSimulation();
                    break;
                case "4":
                    stressTest();
                    break;
                case "5":
                    hospitalWorkflowScenario();
                    break;
                case "6":
                    dataAnalysisReport();
                    break;
                case "7":
                    System.out.println("\nOpen http://localhost:8080 in your browser");
                    break;
                case "8":
                    running = false;
                    System.out.println("\nReturning to main menu...");
                    break;
                default:
                    System.out.println("[ERROR] Invalid option!");
            }
        }
    }

    /**
     * Quick simulation with 5 patients
     */
    private void quickSimulation() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("QUICK SIMULATION - 5 PATIENTS");
        System.out.println("-".repeat(60));

        try {
            // Clear data
            events.clear();

            String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
            String[] hospitals = {"City Hospital", "Central Medical", "St. Mary's", "Healthcare Center", "Metro Medical"};

            System.out.println("\n[STEP 1] Admitting Patients...\n");
            for (int i = 0; i < names.length; i++) {
                String patientId = "P" + String.format("%03d", i + 1);
                String eventData = String.format("Patient Register %s: %s at %s", 
                    patientId, names[i], hospitals[i]);
                createEvent(eventData);
                System.out.println("[OK] " + names[i] + " admitted - Event: " + patientId);
                Thread.sleep(200);
            }

            System.out.println("\n[STEP 2] Recording Vital Signs...\n");
            for (int i = 0; i < names.length; i++) {
                String patientId = "P" + String.format("%03d", i + 1);
                String eventData = String.format("Vitals %s: BP=120/80 HR=72 Temp=37.2 O2=98", patientId);
                createEvent(eventData);
                System.out.println("[OK] Vitals recorded for " + names[i]);
                Thread.sleep(200);
            }

            System.out.println("\n[STEP 3] Running Tests...\n");
            for (int i = 0; i < names.length; i++) {
                String patientId = "P" + String.format("%03d", i + 1);
                String[] tests = {"Blood Test", "X-Ray", "CT Scan", "Ultrasound", "ECG"};
                String eventData = String.format("Test %s: %s - Normal", patientId, tests[i]);
                createEvent(eventData);
                System.out.println("[OK] " + tests[i] + " completed for " + names[i]);
                Thread.sleep(200);
            }

            System.out.println("\n[STEP 4] Treatment Plans...\n");
            for (int i = 0; i < names.length; i++) {
                String patientId = "P" + String.format("%03d", i + 1);
                String eventData = String.format("Treatment %s: Prescribed Medication", patientId);
                createEvent(eventData);
                System.out.println("[OK] Treatment plan created for " + names[i]);
                Thread.sleep(200);
            }

            System.out.println("\n" + "-".repeat(60));
            System.out.println("SIMULATION COMPLETE");
            System.out.println("-".repeat(60));
            System.out.println("Total Events Created: " + events.size());
            System.out.println("Chain Status: " + (validateChain() ? "VALID ?" : "BROKEN ?"));
            System.out.println("Web Dashboard: http://localhost:8080");

        } catch (InterruptedException e) {
            System.out.println("[ERROR] Simulation interrupted!");
        }
    }

    /**
     * Advanced custom simulation
     */
    private void advancedSimulation() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("ADVANCED SIMULATION - CUSTOM");
        System.out.println("-".repeat(60));

        System.out.print("\nEnter number of patients to simulate (1-100): ");
        String input = scanner.nextLine().trim();

        try {
            int numPatients = Integer.parseInt(input);
            if (numPatients < 1 || numPatients > 100) {
                System.out.println("[ERROR] Number must be between 1 and 100!");
                return;
            }

            events.clear();

            System.out.println("\n[PROCESSING] Generating " + numPatients + " patients...\n");

            for (int i = 1; i <= numPatients; i++) {
                String patientId = "P" + String.format("%04d", i);
                String name = generateRandomName();
                String hospital = generateRandomHospital();

                String eventData = String.format("Patient Register %s: %s at %s", 
                    patientId, name, hospital);
                createEvent(eventData);

                // Add vitals
                String vitalsData = String.format("Vitals %s: BP=120/80 HR=72 Temp=37.2 O2=98", patientId);
                createEvent(vitalsData);

                if (i % 10 == 0) {
                    System.out.println("[OK] Processed " + i + " patients... (" + events.size() + " events)");
                }

                Thread.sleep(10);
            }

            System.out.println("\n" + "-".repeat(60));
            System.out.println("ADVANCED SIMULATION COMPLETE");
            System.out.println("-".repeat(60));
            System.out.println("Total Patients: " + numPatients);
            System.out.println("Total Events: " + events.size());
            System.out.println("Chain Status: " + (validateChain() ? "VALID ?" : "BROKEN ?"));

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid number!");
        } catch (InterruptedException e) {
            System.out.println("[ERROR] Simulation interrupted!");
        }
    }

    /**
     * Stress test with 1000 events
     */
    private void stressTest() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("STRESS TEST - 1000 BLOCKCHAIN EVENTS");
        System.out.println("-".repeat(60));

        try {
            events.clear();
            long startTime = System.currentTimeMillis();

            System.out.println("\n[PROCESSING] Creating 1000 events...\n");

            for (int i = 1; i <= 1000; i++) {
                String eventData = String.format("Event %04d: Blockchain stress test data", i);
                createEvent(eventData);

                if (i % 100 == 0) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    System.out.println("[OK] " + i + " events created in " + elapsed + "ms");
                }
            }

            long totalTime = System.currentTimeMillis() - startTime;

            System.out.println("\n" + "-".repeat(60));
            System.out.println("STRESS TEST RESULTS");
            System.out.println("-".repeat(60));
            System.out.println("Total Events: " + events.size());
            System.out.println("Total Time: " + totalTime + "ms");
            System.out.println("Average per Event: " + (totalTime / 1000.0) + "ms");
            System.out.println("Events per Second: " + (1000000.0 / totalTime));
            System.out.println("Chain Status: " + (validateChain() ? "VALID ?" : "BROKEN ?"));
            System.out.println("Memory Used: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");

        } catch (Exception e) {
            System.out.println("[ERROR] Stress test failed: " + e.getMessage());
        }
    }

    /**
     * Hospital workflow scenario
     */
    private void hospitalWorkflowScenario() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("HOSPITAL WORKFLOW SCENARIO");
        System.out.println("-".repeat(60));

        try {
            events.clear();

            System.out.println("\n=== PATIENT ADMISSION ===\n");
            createEvent("Patient Register P001: John Smith at General Hospital");
            System.out.println("[OK] Patient P001 admitted");
            Thread.sleep(300);

            System.out.println("\n=== INITIAL ASSESSMENT ===\n");
            createEvent("Assessment P001: Initial checkup - Normal vitals");
            System.out.println("[OK] Initial assessment completed");
            Thread.sleep(300);

            System.out.println("\n=== DIAGNOSTIC TESTS ===\n");
            createEvent("Test P001: Blood Test - Complete Blood Count");
            System.out.println("[OK] Blood test ordered");
            Thread.sleep(200);
            createEvent("Test P001: X-Ray - Chest X-Ray");
            System.out.println("[OK] X-ray ordered");
            Thread.sleep(200);
            createEvent("Test P001: Imaging - CT Scan");
            System.out.println("[OK] CT scan ordered");
            Thread.sleep(300);

            System.out.println("\n=== TEST RESULTS ===\n");
            createEvent("Result P001: Blood Test - Normal");
            System.out.println("[OK] Blood test results: Normal");
            Thread.sleep(200);
            createEvent("Result P001: X-Ray - No abnormalities");
            System.out.println("[OK] X-ray results: No abnormalities");
            Thread.sleep(200);
            createEvent("Result P001: CT Scan - Normal");
            System.out.println("[OK] CT scan results: Normal");
            Thread.sleep(300);

            System.out.println("\n=== TREATMENT ===\n");
            createEvent("Treatment P001: Prescribed Medication A");
            System.out.println("[OK] Medication A prescribed");
            Thread.sleep(200);
            createEvent("Treatment P001: Physical Therapy Session");
            System.out.println("[OK] Physical therapy scheduled");
            Thread.sleep(300);

            System.out.println("\n=== FOLLOW-UP ===\n");
            createEvent("FollowUp P001: Vital Signs - BP=120/80 HR=72");
            System.out.println("[OK] Follow-up vitals recorded");
            Thread.sleep(300);

            System.out.println("\n=== DISCHARGE ===\n");
            createEvent("Discharge P001: Patient discharged - Recovered");
            System.out.println("[OK] Patient discharged successfully");

            System.out.println("\n" + "-".repeat(60));
            System.out.println("WORKFLOW SCENARIO COMPLETE");
            System.out.println("-".repeat(60));
            System.out.println("Total Events: " + events.size());
            System.out.println("Chain Status: " + (validateChain() ? "VALID ?" : "BROKEN ?"));

        } catch (InterruptedException e) {
            System.out.println("[ERROR] Scenario interrupted!");
        }
    }

    /**
     * Data analysis and reporting
     */
    private void dataAnalysisReport() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("DATA ANALYSIS & REPORTS");
        System.out.println("-".repeat(60));

        System.out.println("\nBlockchain Statistics:");
        System.out.println("  Total Events: " + events.size());
        System.out.println("  Chain Status: " + (validateChain() ? "VALID ?" : "BROKEN ?"));
        System.out.println("  Patients Registered: " + registration.getPatientCount());

        if (!events.isEmpty()) {
            System.out.println("\nFirst Event:");
            BlockchainEvent first = events.get(0);
            System.out.println("  Data: " + first.getData());
            System.out.println("  Hash: " + first.getHash().substring(0, 32) + "...");

            System.out.println("\nLast Event:");
            BlockchainEvent last = events.get(events.size() - 1);
            System.out.println("  Data: " + last.getData());
            System.out.println("  Hash: " + last.getHash().substring(0, 32) + "...");

            System.out.println("\nEvent Types:");
            int registerCount = 0, vitalsCount = 0, testCount = 0, treatmentCount = 0, dischargeCount = 0;
            for (BlockchainEvent e : events) {
                String data = e.getData().toLowerCase();
                if (data.contains("register")) registerCount++;
                else if (data.contains("vitals")) vitalsCount++;
                else if (data.contains("test")) testCount++;
                else if (data.contains("treatment")) treatmentCount++;
                else if (data.contains("discharge")) dischargeCount++;
            }

            System.out.println("  Registrations: " + registerCount);
            System.out.println("  Vital Signs: " + vitalsCount);
            System.out.println("  Tests: " + testCount);
            System.out.println("  Treatments: " + treatmentCount);
            System.out.println("  Discharges: " + dischargeCount);
        }

        System.out.println("\nPatient Records:");
        for (PatientRegistration.Patient p : registration.getPatients()) {
            System.out.println("  " + p);
        }
    }

    /**
     * Helper methods
     */
    private void createEvent(String data) {
        try {
            BlockchainEvent event = new BlockchainEvent(data,
                events.isEmpty() ? "0" : events.get(events.size() - 1).getHash());
            events.add(event);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to create event: " + e.getMessage());
        }
    }

    private boolean validateChain() {
        if (events.isEmpty()) return true;
        String expectedPrevHash = "0";
        for (BlockchainEvent event : events) {
            if (!event.getPrevHash().equals(expectedPrevHash)) {
                return false;
            }
            expectedPrevHash = event.getHash();
        }
        return true;
    }

    private String generateRandomName() {
        String[] names = {"John", "Jane", "Michael", "Sarah", "Robert", "Emma", "James", "Olivia", "David", "Sophia"};
        String[] surnames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        return names[(int)(Math.random() * names.length)] + " " + surnames[(int)(Math.random() * surnames.length)];
    }

    private String generateRandomHospital() {
        String[] hospitals = {"City Hospital", "Central Medical", "St. Mary's Hospital", "Healthcare Center", "Metro Medical", "University Medical", "Community Hospital", "Regional Medical Center"};
        return hospitals[(int)(Math.random() * hospitals.length)];
    }
}
