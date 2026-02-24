# ?? QUICK START GUIDE - How to Run Your EDSC System

## ? 3 Easy Ways to Start

### **Option 1: Using Windows Batch File (Easiest for Windows)**

```bash
# Double-click this file in File Explorer:
start.bat

# OR run from Command Prompt:
start.bat
```

**What happens:**
1. ? Checks Java is installed
2. ? Compiles all source code
3. ? Shows you a menu
4. ? Runs your chosen option

---

### **Option 2: Using PowerShell (Windows 10/11)**

```powershell
# Open PowerShell and navigate to project folder:
cd D:\blockchainProjekt

# Run the startup script:
.\start.ps1

# If you get an error about execution policy, run this first:
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

---

### **Option 3: Using Command Line (Manual)**

#### **Windows (CMD):**
```cmd
cd D:\blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
java -cp out/production/blockchainProjekt com.company.Main
```

#### **Linux/Mac (Terminal):**
```bash
cd /path/to/blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
java -cp out/production/blockchainProjekt com.company.Main
```

---

## ?? What You'll See (Menu)

After running any option, you'll see:

```
??????????????????????????????????????????????
?           EDSC Control Menu                ?
??????????????????????????????????????????????
?  1. Run Unit Tests (EDSCTest)             ?
?  2. View System Information               ?
?  3. Exit                                  ?
??????????????????????????????????????????????

Select option (1-3): _
```

**Choose:**
- **1** = Run tests to verify everything works
- **2** = See system architecture and ports
- **3** = Exit the program

---

## ?? First Time Setup (Complete Steps)

### **Step 1: Open Terminal/Command Prompt**

**Windows:**
- Press `Win + R`
- Type `cmd` and press Enter
- OR double-click `start.bat`

**Mac/Linux:**
- Open Terminal application
- Navigate to project folder

### **Step 2: Navigate to Project**

```bash
cd D:\blockchainProjekt
```

### **Step 3: Choose Your Launch Method**

**For Windows users - Easiest:**
```bash
start.bat
```

**For Everyone - Manual:**
```bash
# Compile (one-time or when code changes)
javac -d out/production/blockchainProjekt src/com/company/*.java

# Run full system
java -cp out/production/blockchainProjekt com.company.Main

# OR run just tests
java -cp out/production/blockchainProjekt com.company.EDSCTest
```

### **Step 4: Watch the Magic! ?**

You'll see output like:
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
...
```

---

## ?? Running Unit Tests Only

Want to just test the blockchain without full system?

**Quick command:**
```bash
java -cp out/production/blockchainProjekt com.company.EDSCTest
```

**Expected output:**
```
=== Event-Driven Smart Contract Platform - Test Suite ===

--- Test 1: Adding Patient Data Events ---
? Event 1: Patient Register 12345
  Hash: a1b2c3d4...
  Prev Hash: 0

--- Test 2: Validating Events ---
? Event valid: Patient Register 12345 - true

--- Test 4: Event Chain Integrity ---
? Event 0: Chain link valid - true
? Event 1: Chain link valid - true

=== Summary ===
Total Events: 3
Chain Status: ? Valid
Platform Status: ? Ready for distributed network
```

---

## ?? Files & What They Do

| File | Purpose |
|------|---------|
| `Main.java` | ?? Main entry point with menu |
| `EDSC.java` | ?? Blockchain engine |
| `BlockchainEvent.java` | ?? Individual blockchain event |
| `Broker.java` | ?? Message broker for hospitals |
| `Hospital.java` | ?? Hospital system |
| `EDSCTest.java` | ?? Unit tests |
| `start.bat` | ?? Windows launcher |
| `start.ps1` | ?? PowerShell launcher |
| `start.sh` | ?? Linux/Mac launcher |
| `README.md` | ?? Detailed documentation |

---

## ?? Troubleshooting

### **Error: "Java is not recognized"**
**Solution:** Java is not installed or not in PATH
- Download Java: https://www.java.com/en/download/
- Restart terminal after installation
- Test with: `java -version`

### **Error: "Port already in use"**
**Solution:** Another program is using ports 8888 or 9999
- Option A: Close other Java programs
- Option B: Change ports in Main.java (lines with 8888 and 9999)

### **Error: "Cannot find symbol"**
**Solution:** Clean and rebuild
```bash
rmdir /s out
javac -d out/production/blockchainProjekt src/com/company/*.java
```

### **Error: "FileNotFound"**
**Solution:** Make sure you're in the correct directory
```bash
# Check you're here:
cd D:\blockchainProjekt
dir  # Should show: src, out, start.bat, README.md, etc.
```

---

## ?? Quick Reference Commands

```bash
# Compile code
javac -d out/production/blockchainProjekt src/com/company/*.java

# Run full system with menu
java -cp out/production/blockchainProjekt com.company.Main

# Run just the tests
java -cp out/production/blockchainProjekt com.company.EDSCTest

# Run just the broker+hospitals
java -cp out/production/blockchainProjekt com.company.Broker

# Check Java version
java -version

# Navigate to project
cd D:\blockchainProjekt
```

---

## ?? What's Happening Behind the Scenes?

```
1. You run the program
   ?
2. Broker starts (Port 8888)
   ?? Waits for hospitals to connect
   ?
3. EDSC Blockchain starts (Port 9999)
   ?? Creates blockchain event log
   ?
4. Hospitals connect (Hospital 1 & 2)
   ?? Admit patients
   ?? Send patient data to broker
   ?
5. Broker broadcasts data
   ?? Sends vital signs to correct hospital
   ?
6. EDSC records all events
   ?? Validates using SHA-256
   ?? Creates immutable chain
   ?
7. All data verified and stored!
```

---

## ?? Pro Tips

- **First time?** ? Run `start.bat` (easiest)
- **Want to modify code?** ? Edit Java files, then run compile command
- **Need to test?** ? Run `EDSCTest.java`
- **Check system health?** ? Select option 2 in menu
- **Want details?** ? Read `README.md`

---

## ?? You're Ready!

Pick your launch method above and enjoy your blockchain system!

**Questions?** Check README.md for detailed documentation.

---

**Happy coding! ??**
