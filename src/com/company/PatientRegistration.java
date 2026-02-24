package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Patient Registration and Simulation System for EDSC
 * Allows interactive patient registration and blockchain event simulation
 */
public class PatientRegistration {
    private EDSC edsc;
    private List<BlockchainEvent> events;
    private List<Patient> patients;
    private Scanner scanner;

    public static class Patient {
        public String id;
        public String name;
        public String hospital;
        public String admissionTime;
        public String status;

        public Patient(String id, String name, String hospital) {
            this.id = id;
            this.name = name;
            this.hospital = hospital;
            this.admissionTime = java.time.LocalDateTime.now().toString();
            this.status = "Admitted";
        }

        @Override
        public String toString() {
            return String.format("[%s] %s - %s (%s) - %s", id, name, hospital, admissionTime, status);
        }
    }

    public PatientRegistration(EDSC edsc, List<BlockchainEvent> events) {
        this.edsc = edsc;
        this.events = events;
        this.patients = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main interactive menu for patient registration and simulation
     */
    public void startInteractiveMode() {
        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           PATIENT REGISTRATION & SIMULATION");
            System.out.println("=".repeat(60));
            System.out.println("1. Register New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Add Patient Vital Signs");
            System.out.println("4. Simulate Hospital Discharge");
            System.out.println("5. View Blockchain Events");
            System.out.println("6. Run Full Simulation");
            System.out.println("7. Generate Test Data");
            System.out.println("8. Export Patient Records");
            System.out.println("9. Back to Main Menu");
            System.out.println("=".repeat(60));
            System.out.print("Select option (1-9): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerNewPatient();
                    break;
                case "2":
                    viewAllPatients();
                    break;
                case "3":
                    addVitalSigns();
                    break;
                case "4":
                    simulateDischarge();
                    break;
                case "5":
                    viewBlockchainEvents();
                    break;
                case "6":
                    runFullSimulation();
                    break;
                case "7":
                    generateTestData();
                    break;
                case "8":
                    exportPatientRecords();
                    break;
                case "9":
                    running = false;
                    System.out.println("\nReturning to main menu...");
                    break;
                default:
                    System.out.println("[ERROR] Invalid option. Please select 1-9.");
            }
        }
    }

    /**
     * Interactive patient registration
     */
    private void registerNewPatient() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("REGISTER NEW PATIENT");
        System.out.println("-".repeat(60));

        System.out.print("Enter Patient ID (e.g., P001): ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("[ERROR] Patient ID cannot be empty!");
            return;
        }

        // Check if ID already exists
        for (Patient p : patients) {
            if (p.id.equals(id)) {
                System.out.println("[ERROR] Patient ID already exists!");
                return;
            }
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("[ERROR] Patient Name cannot be empty!");
            return;
        }

        System.out.print("Enter Hospital Name: ");
        String hospital = scanner.nextLine().trim();

        if (hospital.isEmpty()) {
            System.out.println("[ERROR] Hospital Name cannot be empty!");
            return;
        }

        // Create patient
        Patient patient = new Patient(id, name, hospital);
        patients.add(patient);

        // Add blockchain event
        try {
            String eventData = String.format("Patient Register %s: %s at %s", id, name, hospital);
            BlockchainEvent event = new BlockchainEvent(eventData, 
                events.isEmpty() ? "0" : events.get(events.size() - 1).getHash());
            events.add(event);
            System.out.println("[OK] Patient registered successfully!");
            System.out.println("    " + patient);
            System.out.println("    Event Hash: " + event.getHash().substring(0, 16) + "...");
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to register patient: " + e.getMessage());
        }
    }

    /**
     * View all registered patients
     */
    private void viewAllPatients() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("REGISTERED PATIENTS (" + patients.size() + " total)");
        System.out.println("-".repeat(60));

        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }

        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i));
        }
    }

    /**
     * Add vital signs for a patient (creates blockchain event)
     */
    private void addVitalSigns() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("ADD PATIENT VITAL SIGNS");
        System.out.println("-".repeat(60));

        if (patients.isEmpty()) {
            System.out.println("No patients registered. Please register a patient first.");
            return;
        }

        System.out.println("Select Patient:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).name + " (" + patients.get(i).id + ")");
        }

        System.out.print("Enter choice (1-" + patients.size() + "): ");
        String choice = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index < 0 || index >= patients.size()) {
                System.out.println("[ERROR] Invalid choice!");
                return;
            }

            Patient patient = patients.get(index);

            System.out.println("\nEnter Vital Signs for " + patient.name + ":");
            System.out.print("Blood Pressure (e.g., 120/80): ");
            String bp = scanner.nextLine().trim();

            System.out.print("Heart Rate (bpm): ");
            String hr = scanner.nextLine().trim();

            System.out.print("Temperature (C): ");
            String temp = scanner.nextLine().trim();

            System.out.print("Oxygen Level (%): ");
            String o2 = scanner.nextLine().trim();

            if (bp.isEmpty() || hr.isEmpty() || temp.isEmpty() || o2.isEmpty()) {
                System.out.println("[ERROR] All fields are required!");
                return;
            }

            // Create blockchain event
            String eventData = String.format("Vitals %s: BP=%s HR=%s Temp=%s O2=%s", 
                patient.id, bp, hr, temp, o2);
            BlockchainEvent event = new BlockchainEvent(eventData,
                events.isEmpty() ? "0" : events.get(events.size() - 1).getHash());
            events.add(event);

            System.out.println("[OK] Vital signs recorded successfully!");
            System.out.println("    Event: " + eventData);
            System.out.println("    Hash: " + event.getHash().substring(0, 16) + "...");

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid input!");
        }
    }

    /**
     * Simulate patient discharge
     */
    private void simulateDischarge() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("DISCHARGE PATIENT");
        System.out.println("-".repeat(60));

        if (patients.isEmpty()) {
            System.out.println("No patients to discharge.");
            return;
        }

        System.out.println("Select Patient to Discharge:");
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).status.equals("Admitted")) {
                System.out.println((i + 1) + ". " + patients.get(i).name + " (" + patients.get(i).id + ")");
            }
        }

        System.out.print("Enter choice: ");
        String choice = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index < 0 || index >= patients.size()) {
                System.out.println("[ERROR] Invalid choice!");
                return;
            }

            Patient patient = patients.get(index);

            System.out.print("Discharge Reason: ");
            String reason = scanner.nextLine().trim();

            if (reason.isEmpty()) {
                reason = "Regular Discharge";
            }

            // Update patient status
            patient.status = "Discharged";

            // Create blockchain event
            String eventData = String.format("Discharge %s: %s", patient.id, reason);
            BlockchainEvent event = new BlockchainEvent(eventData,
                events.isEmpty() ? "0" : events.get(events.size() - 1).getHash());
            events.add(event);

            System.out.println("[OK] Patient discharged successfully!");
            System.out.println("    Patient: " + patient.name);
            System.out.println("    Reason: " + reason);
            System.out.println("    Event Hash: " + event.getHash().substring(0, 16) + "...");

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid input!");
        }
    }

    /**
     * View all blockchain events
     */
    private void viewBlockchainEvents() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("BLOCKCHAIN EVENTS (" + events.size() + " total)");
        System.out.println("-".repeat(60));

        if (events.isEmpty()) {
            System.out.println("No events recorded yet.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            BlockchainEvent event = events.get(i);
            System.out.println("\nEvent #" + i);
            System.out.println("  Data: " + event.getData());
            System.out.println("  Hash: " + event.getHash());
            System.out.println("  Prev: " + event.getPrevHash());
            System.out.println("  Time: " + new java.util.Date(event.getTimestamp()));
        }

        // Show chain status
        System.out.println("\n" + "-".repeat(60));
        System.out.println("Chain Integrity: " + (validateChain() ? "VALID" : "BROKEN"));
        System.out.println("-".repeat(60));
    }

    /**
     * Run a complete simulation with predefined data
     */
    private void runFullSimulation() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("RUNNING FULL SIMULATION");
        System.out.println("-".repeat(60));

        try {
            // Clear previous data
            patients.clear();
            events.clear();

            System.out.println("\n[STEP 1] Registering Patients...");
            Patient p1 = new Patient("P001", "John Smith", "General Hospital");
            patients.add(p1);
            createEvent("Patient Register P001: John Smith at General Hospital");
            System.out.println("[OK] Patient 1 registered");

            Patient p2 = new Patient("P002", "Jane Doe", "Central Medical");
            patients.add(p2);
            createEvent("Patient Register P002: Jane Doe at Central Medical");
            System.out.println("[OK] Patient 2 registered");

            Thread.sleep(500);

            System.out.println("\n[STEP 2] Recording Vital Signs...");
            createEvent("Vitals P001: BP=120/80 HR=72 Temp=37.2 O2=98");
            System.out.println("[OK] Vitals recorded for Patient 1");

            createEvent("Vitals P002: BP=118/78 HR=68 Temp=36.8 O2=99");
            System.out.println("[OK] Vitals recorded for Patient 2");

            Thread.sleep(500);

            System.out.println("\n[STEP 3] Performing Tests...");
            createEvent("Test P001: Blood Test - Normal");
            System.out.println("[OK] Blood test completed for Patient 1");

            createEvent("Test P002: X-Ray - No abnormalities");
            System.out.println("[OK] X-ray completed for Patient 2");

            Thread.sleep(500);

            System.out.println("\n[STEP 4] Updating Treatment...");
            createEvent("Treatment P001: Prescribed Antibiotics");
            System.out.println("[OK] Treatment updated for Patient 1");

            createEvent("Treatment P002: Prescribed Vitamins");
            System.out.println("[OK] Treatment updated for Patient 2");

            Thread.sleep(500);

            System.out.println("\n[STEP 5] Discharging Patients...");
            p1.status = "Discharged";
            createEvent("Discharge P001: Regular Discharge - Recovered");
            System.out.println("[OK] Patient 1 discharged");

            System.out.println("\n" + "-".repeat(60));
            System.out.println("SIMULATION COMPLETE");
            System.out.println("-".repeat(60));
            System.out.println("Total Events: " + events.size());
            System.out.println("Total Patients: " + patients.size());
            System.out.println("Chain Status: " + (validateChain() ? "VALID" : "BROKEN"));

        } catch (InterruptedException e) {
            System.out.println("[ERROR] Simulation interrupted: " + e.getMessage());
        }
    }

    /**
     * Generate realistic test data
     */
    private void generateTestData() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("GENERATING TEST DATA");
        System.out.println("-".repeat(60));

        try {
            patients.clear();
            events.clear();

            // Sample data
            String[][] patientData = {
                {"P001", "Alice Johnson", "City Hospital"},
                {"P002", "Bob Williams", "Central Medical"},
                {"P003", "Charlie Brown", "St. Mary's Hospital"},
                {"P004", "Diana Prince", "Healthcare Center"},
                {"P005", "Eve Wilson", "Metro Medical"}
            };

            System.out.println("Registering " + patientData.length + " patients...\n");

            for (String[] data : patientData) {
                Patient p = new Patient(data[0], data[1], data[2]);
                patients.add(p);
                createEvent("Patient Register " + data[0] + ": " + data[1] + " at " + data[2]);
                System.out.println("[OK] " + data[1] + " registered");
                Thread.sleep(100);
            }

            System.out.println("\nAdding vital signs...\n");
            String[][] vitals = {
                {"P001", "120/80", "72", "37.2", "98"},
                {"P002", "118/78", "68", "36.8", "99"},
                {"P003", "125/82", "75", "37.5", "97"},
                {"P004", "115/75", "65", "37.0", "99"},
                {"P005", "130/85", "80", "37.8", "96"}
            };

            for (String[] v : vitals) {
                createEvent(String.format("Vitals %s: BP=%s HR=%s Temp=%s O2=%s", 
                    v[0], v[1], v[2], v[3], v[4]));
                System.out.println("[OK] Vitals added for " + v[0]);
                Thread.sleep(100);
            }

            System.out.println("\n" + "-".repeat(60));
            System.out.println("TEST DATA GENERATED");
            System.out.println("-".repeat(60));
            System.out.println("Total Patients: " + patients.size());
            System.out.println("Total Events: " + events.size());
            System.out.println("Chain Status: " + (validateChain() ? "VALID" : "BROKEN"));

        } catch (InterruptedException e) {
            System.out.println("[ERROR] Data generation interrupted!");
        }
    }

    /**
     * Export patient records
     */
    private void exportPatientRecords() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("PATIENT RECORDS EXPORT");
        System.out.println("-".repeat(60));

        if (patients.isEmpty()) {
            System.out.println("No patient records to export.");
            return;
        }

        System.out.println("\nPatient Records:\n");
        System.out.println(String.format("%-6s %-20s %-20s %-12s %-12s",
            "ID", "Name", "Hospital", "Status", "Admission"));
        System.out.println("-".repeat(70));

        for (Patient p : patients) {
            System.out.println(String.format("%-6s %-20s %-20s %-12s %-12s",
                p.id, p.name, p.hospital, p.status, p.admissionTime.substring(0, 10)));
        }

        System.out.println("\nBlockchain Events: " + events.size());
        System.out.println("Chain Integrity: " + (validateChain() ? "VALID" : "BROKEN"));
    }

    /**
     * Helper method to create blockchain event
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

    /**
     * Validate blockchain chain integrity
     */
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

    /**
     * Get patient count
     */
    public int getPatientCount() {
        return patients.size();
    }

    /**
     * Get event count
     */
    public int getEventCount() {
        return events.size();
    }

    /**
     * Get patient list
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }
}
