# ?? Event-Driven Smart Contract Platform (EDSC) - Quick Start Guide

## ?? Overview

This is a **Patient Data Management System** using **Blockchain** and **Event-Driven Architecture** with:
- **Broker**: Manages patient-hospital relationships
- **EDSC**: Immutable blockchain event ledger
- **Hospitals**: Admit patients and receive vital signs
- **Multi-threaded Socket Communication**: Real-time data sync

---

## ??? Installation & Setup

### Prerequisites
- **Java 8+** installed
- Command line terminal (PowerShell, CMD, or Terminal)

### Project Structure
```
blockchainProjekt/
??? src/
?   ??? com/
?       ??? company/
?           ??? Main.java              (Entry point)
?           ??? EDSC.java              (Blockchain)
?           ??? BlockchainEvent.java   (Event class)
?           ??? Broker.java            (Message broker)
?           ??? Hospital.java          (Hospital system)
?           ??? EDSCTest.java          (Unit tests)
?           ??? README.md              (This file)
??? out/
    ??? production/blockchainProjekt/  (Compiled classes)
```

---

## ?? How to Start

### **Method 1: Using IDE (IntelliJ IDEA / Eclipse)**

1. **Open the project**
   - File ? Open ? Select `blockchainProjekt` folder

2. **Run Main.java**
   - Right-click on `src/com/company/Main.java`
   - Select **Run 'Main.main()'**

3. **Follow the interactive menu**
   - Choose option 1 to run tests
   - Choose option 2 to view system info
   - Choose option 3 to exit

---

### **Method 2: Using Command Line**

#### **Step 1: Compile the code**
```bash
cd D:\blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
```

#### **Step 2: Run the Main program**
```bash
cd D:\blockchainProjekt
java -cp out/production/blockchainProjekt com.company.Main
```

---

### **Method 3: Using PowerShell (Recommended for Windows)**

```powershell
# Navigate to project directory
cd D:\blockchainProjekt

# Compile all Java files
javac -d out/production/blockchainProjekt src/com/company/*.java

# Run the main program
java -cp out/production/blockchainProjekt com.company.Main

# Run unit tests only (alternative)
java -cp out/production/blockchainProjekt com.company.EDSCTest
```

---

## ?? Expected Output

When you run `Main.main()`, you'll see:

```
??????????????????????????????????????????????????????????????????
?   Event-Driven Smart Contract Platform (EDSC)                  ?
?   Patient Data Management with Blockchain                      ?
??????????????????????????????????????????????????????????????????

[STEP 1] Starting Broker on port 8888...
Broker started
? Broker started successfully

[STEP 2] Starting EDSC (Event-Driven Smart Contract) on port 9999...
EDSC started on localhost:9999
? EDSC started successfully

[STEP 3] Starting Hospitals...
Hospital 1 started
Hospital 2 started
? Hospitals connected to broker

[STEP 4] Admitting Patients...
Hospital 1 admitted patient: P001 - Alice Johnson
Hospital 1 admitted patient: P002 - Bob Smith
Hospital 2 admitted patient: P003 - Charlie Brown
Hospital 2 admitted patient: P004 - Diana Prince
? Patients admitted successfully

[STEP 5] Adding EDSC Events (Blockchain)...
New event added: print Hospital_Network_Active
New event added: print Patient_Records_Synced
New event added: print Data_Verification_Complete
? Events added to blockchain

[STEP 6] Broadcasting Patient Data...
Broker sent data for patient: P001: Blood Pressure: 120/80 mmHg
Broker sent data for patient: P002: Heart Rate: 72 bpm
...

??????????????????????????????????????????????????????????????????
?                    SYSTEM INITIALIZATION COMPLETE              ?
??????????????????????????????????????????????????????????????????

??????????????????????????????????????????????
?           EDSC Control Menu                ?
??????????????????????????????????????????????
?  1. Run Unit Tests (EDSCTest)             ?
?  2. View System Information               ?
?  3. Exit                                  ?
??????????????????????????????????????????????

Select option (1-3): _
```

---

## ?? Running Unit Tests

### **Option A: From Main Menu**
1. Run `Main.main()`
2. Select **Option 1** ? Runs `EDSCTest.main()`

### **Option B: Direct Test Run**

**Command Line:**
```bash
java -cp out/production/blockchainProjekt com.company.EDSCTest
```

**Expected Test Output:**
```
=== Event-Driven Smart Contract Platform - Test Suite ===

--- Test 1: Adding Patient Data Events ---
? Event 1: Patient Register 12345
  Hash: a1b2c3d4ef567890...
  Prev Hash: 0

? Event 2: Appointment scheduled P12345
  Hash: e5f6g7h8ij901234...
  Prev Hash: a1b2c3d4ef567890...

--- Test 2: Validating Events ---
? Event valid: Patient Register 12345 - true
? Event valid: Appointment scheduled P12345 - true

--- Test 4: Event Chain Integrity ---
? Event 0: Chain link valid - true
? Event 1: Chain link valid - true

=== Summary ===
Total Events: 3
Chain Status: ? Valid
Platform Status: ? Ready for distributed network
```

---

## ?? Class Overview

### **1. EDSC.java** (Event-Driven Smart Contract)
- Manages blockchain events
- Validates hash chains
- Triggers actions based on events
- Socket-based node communication

### **2. BlockchainEvent.java**
- Represents a single blockchain event
- SHA-256 hashing
- Timestamp management
- Previous hash linking

### **3. Broker.java**
- Acts as message broker
- Maps patients to hospitals
- Broadcasts vital signs data
- Multi-threaded listener

### **4. Hospital.java**
- Admits patients
- Receives vital signs from broker
- Communicates via socket to broker
- Patient tracking

### **5. Main.java**
- Entry point for entire system
- Orchestrates startup sequence
- Provides interactive menu
- System information display

### **6. EDSCTest.java**
- Comprehensive unit tests
- Validates blockchain integrity
- Tests event processing
- Edge case handling

---

## ?? Troubleshooting

### **Problem: "Port already in use"**
**Solution:** Change port numbers in Main.java
```java
// Change Broker port from 8888 to 9999
Broker broker = new Broker(9999);

// Change EDSC port from 9999 to 10000
EDSC edsc = new EDSC("localhost", 10000);
```

### **Problem: "Connection refused"**
**Solution:** Ensure components start in correct order:
1. Broker starts first
2. EDSC starts second
3. Hospitals connect last

### **Problem: "Compilation errors"**
**Solution:**
```bash
# Clean and rebuild
rmdir /s out\production\blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
```

### **Problem: "No output after running"**
**Solution:** Add output redirection
```bash
java -cp out/production/blockchainProjekt com.company.Main 2>&1
```

---

## ?? Quick Commands Reference

| Command | Purpose |
|---------|---------|
| `javac -d out/production/blockchainProjekt src/com/company/*.java` | Compile all Java files |
| `java -cp out/production/blockchainProjekt com.company.Main` | Run main program |
| `java -cp out/production/blockchainProjekt com.company.EDSCTest` | Run unit tests |
| `cd D:\blockchainProjekt` | Navigate to project |

---

## ?? Data Flow Architecture

```
????????????????????????????????????????????????????????????????
?                    System Architecture                        ?
????????????????????????????????????????????????????????????????

    Hospital 1               Hospital 2
        ?                        ?
        ??????????????????????????
                 ?
         [Broker - Port 8888]
                 ?
         Manages patient-hospital
         mapping and broadcasts
         vital signs
                 ?
        ??????????????????????
        ?                    ?
     Hospital 1          Hospital 2
     receives data       receives data
        ?                    ?
        ??????????????????????
                 ?
        [EDSC - Port 9999]
                 ?
         Immutable blockchain
         for all events
                 ?
      Validates and stores
      all transactions
```

---

## ?? Security Features

? **SHA-256 Hashing** - Cryptographic event validation
? **Chain Integrity** - Previous hash verification
? **Immutable Records** - Event chain cannot be modified
? **Multi-threaded** - Concurrent event processing
? **Socket Synchronization** - Thread-safe operations

---

## ?? License

This is a patient data management system using blockchain technology for educational purposes.

---

## ?? Need Help?

1. **Check this README first**
2. **Run EDSCTest.java** to verify system integrity
3. **Check console output** for error messages
4. **Verify all ports are available** (8888, 9999)

---

**Happy coding! ??**
