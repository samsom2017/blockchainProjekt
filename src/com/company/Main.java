package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the Event-Driven Smart Contract Platform
 * This demonstrates a complete workflow with Broker, Hospitals, and EDSC
 */
public class Main {
    // Global variables to share between menu items
    private static EDSC globalEdsc;
    private static List<BlockchainEvent> globalEventList;
    private static WebServer webServer;

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("   Event-Driven Smart Contract Platform (EDSC)");
        System.out.println("   Patient Data Management with Blockchain");
        System.out.println("====================================================================\n");

        try {
            // Step 1: Start Broker on port 8888
            System.out.println("[STEP 1] Starting Broker on port 8888...");
            Broker broker = new Broker(8888);
            new Thread(() -> {
                try {
                    broker.acceptConnections();
                } catch (IOException e) {
                    System.err.println("Broker error: " + e.getMessage());
                }
            }).start();
            System.out.println("[OK] Broker started successfully\n");

            // Step 2: Wait for broker to be ready
            Thread.sleep(1000);

            // Step 3: Create shared event list and EDSC
            System.out.println("[STEP 2] Starting EDSC (Event-Driven Smart Contract) on port 9999...");
            globalEventList = new ArrayList<>();
            globalEdsc = new EDSC(globalEventList, null);
            System.out.println("[OK] EDSC started successfully\n");

            // Step 3.5: Start Web UI on port 8080
            System.out.println("[STEP 2.5] Starting Web UI on port 8080...");
            webServer = new WebServer(8080, globalEdsc, globalEventList);
            webServer.start();
            System.out.println("[OK] Web UI started - Open http://localhost:8080\n");

            // Step 4: Create and start hospitals
            System.out.println("[STEP 3] Starting Hospitals...");
            Hospital hospital1 = new Hospital("Hospital 1", "localhost", 8888);
            Hospital hospital2 = new Hospital("Hospital 2", "localhost", 8888);
            System.out.println("[OK] Hospitals connected to broker\n");

            // Step 5: Wait for all connections to stabilize
            Thread.sleep(1000);

            // Step 6: Admit patients to hospitals
            System.out.println("[STEP 4] Admitting Patients...");
            hospital1.admitPatient("P001", "Alice Johnson");
            hospital1.admitPatient("P002", "Bob Smith");
            hospital2.admitPatient("P003", "Charlie Brown");
            hospital2.admitPatient("P004", "Diana Prince");
            System.out.println("[OK] Patients admitted successfully\n");

            // Step 7: Wait for patient data to be processed
            Thread.sleep(1500);

            // Step 8: Add EDSC events
            System.out.println("[STEP 5] Adding EDSC Events (Blockchain)...");
            try {
                globalEdsc.addEvent("print Hospital_Network_Active");
                Thread.sleep(500);
                globalEdsc.addEvent("print Patient_Records_Synced");
                Thread.sleep(500);
                globalEdsc.addEvent("print Data_Verification_Complete");
            } catch (Exception e) {
                System.out.println("[WARNING] Could not add all events: " + e.getMessage());
            }
            System.out.println("[OK] Events added to blockchain\n");

            // Step 9: Send patient data through broker
            System.out.println("[STEP 6] Broadcasting Patient Data...");
            broker.sendData("P001", "Blood Pressure: 120/80 mmHg");
            Thread.sleep(300);
            broker.sendData("P002", "Heart Rate: 72 bpm");
            Thread.sleep(300);
            broker.sendData("P003", "Temperature: 37.2C");
            Thread.sleep(300);
            broker.sendData("P004", "Oxygen Level: 98%");
            System.out.println("[OK] Patient data broadcasted\n");

            // Step 10: Wait for all messages to be processed
            Thread.sleep(2000);

            // Display completion message
            System.out.println("====================================================================");
            System.out.println("                    SYSTEM INITIALIZATION COMPLETE");
            System.out.println("====================================================================");
            System.out.println("");
            System.out.println("  Components Running:");
            System.out.println("  [OK] Broker (Port 8888) - Manages patient-hospital mapping");
            System.out.println("  [OK] EDSC (Port 9999) - Blockchain event management");
            System.out.println("  [OK] Hospital 1 & 2 - Connected and receiving data");
            System.out.println("  [OK] 4 Patients admitted with vital signs");
            System.out.println("");
            System.out.println("  To test functionality, run: java -cp out/production/blockchainProjekt com.company.EDSCTest");
            System.out.println("");
            System.out.println("====================================================================\n");

            // Interactive menu
            showMenu();

        } catch (IOException e) {
            System.err.println("[ERROR] Error starting system: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("[ERROR] Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====================================================");
            System.out.println("           EDSC Control Menu");
            System.out.println("====================================================");
            System.out.println("  1. Run Unit Tests (EDSCTest)");
            System.out.println("  2. Patient Registration & Simulation");
            System.out.println("  3. View System Information");
            System.out.println("  4. Open Web Dashboard");
            System.out.println("  5. Exit");
            System.out.println("====================================================");
            System.out.print("\nSelect option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n[LAUNCHING] EDSCTest.main()...\n");
                    EDSCTest.main(new String[]{});
                    break;
                case "2":
                    System.out.println("\n[LAUNCHING] Blockchain Simulation Center...\n");
                    // Use the global EDSC and event list instead of creating new ones!
                    BlockchainSimulation sim = new BlockchainSimulation(globalEdsc, globalEventList);
                    sim.startSimulation();
                    System.out.println("\n[OK] Simulation complete! Check dashboard at http://localhost:8080");
                    break;
                case "3":
                    displaySystemInfo();
                    break;
                case "4":
                    System.out.println("\n[DASHBOARD] Open your browser to: http://localhost:8080");
                    System.out.println("[INFO] Current blockchain events: " + globalEventList.size());
                    break;
                case "5":
                    running = false;
                    System.out.println("\n[OK] Shutting down system gracefully...");
                    break;
                default:
                    System.out.println("[ERROR] Invalid option. Please select 1, 2, 3, 4, or 5.");
            }
        }

        scanner.close();
    }

    private static void displaySystemInfo() {
        System.out.println("\n====================================================================");
        System.out.println("                    SYSTEM INFORMATION");
        System.out.println("====================================================================");
        System.out.println("  Component          Port      Function");
        System.out.println("  -------- ------  ----------  --------------------------------");
        System.out.println("  Broker             8888      Patient-Hospital Mapping");
        System.out.println("  EDSC               9999      Blockchain Event Management");
        System.out.println("  Hospital 1         Dynamic   Admits Patients");
        System.out.println("  Hospital 2         Dynamic   Admits Patients");
        System.out.println("  -------- ------  ----------  --------------------------------");
        System.out.println("");
        System.out.println("  Data Flow:");
        System.out.println("  - Hospitals send to Broker (patient registration)");
        System.out.println("  - Broker sends to Hospitals (vital signs broadcast)");
        System.out.println("  - All events go to EDSC (blockchain immutability)");
        System.out.println("");
        System.out.println("  Key Features:");
        System.out.println("  [*] Event-driven architecture");
        System.out.println("  [*] SHA-256 hash-based blockchain");
        System.out.println("  [*] Multi-threaded socket communication");
        System.out.println("  [*] Patient data immutability");
        System.out.println("");
        System.out.println("====================================================================\n");
    }
}

