# ?? ABSOLUTE BEGINNER'S GUIDE TO RUNNING EDSC

> **If this is your first time** - Start here!

---

## ?? Before You Begin

### **Check: Do you have Java?**

Open Command Prompt/Terminal and type:
```bash
java -version
```

**If you see version info:** ? Great! Skip to Step 1
**If you see error:** ? Go to https://www.java.com/en/download/ and install Java first

---

## ?? Step 1: Open Command Prompt

### **Windows Users:**

**Option A (Easiest):**
1. Press `Windows Key + R`
2. Type: `cmd`
3. Press `Enter`

**Option B:**
1. Open File Explorer
2. Navigate to: `D:\blockchainProjekt`
3. Hold `Shift` and Right-Click empty space
4. Click "Open PowerShell window here"

### **Mac/Linux Users:**
1. Open Terminal application

---

## ?? Step 2: Navigate to Your Project

In the Command Prompt/Terminal, type:

```bash
cd D:\blockchainProjekt
```

**Check you're in the right place:**
```bash
dir
```

You should see:
```
 Directory of D:\blockchainProjekt

QUICKSTART.md
README.md
INDEX.md
start.bat
start.ps1
start.sh
src
out
...
```

If you see these files ? ? You're in the right place!

---

## ?? Step 3: Pick ONE of These 3 Options

### **EASIEST - Option A: Click start.bat**

**Windows Only:**

1. In File Explorer, navigate to: `D:\blockchainProjekt`
2. **Double-click** the file named: `start.bat`
3. A menu appears ? Follow it!

**That's it! Skip to Step 4.**

---

### **STANDARD - Option B: Command Line (All Systems)**

**In your Command Prompt/Terminal, type:**

```bash
javac -d out/production/blockchainProjekt src/com/company/*.java
```

Wait for it to finish. Then type:

```bash
java -cp out/production/blockchainProjekt com.company.Main
```

**Skip to Step 4.**

---

### **AUTOMATED - Option C: PowerShell (Windows 10/11)**

**In PowerShell, type:**

```bash
.\start.ps1
```

If you get an error about execution policy, run this first:
```bash
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

**Then try again.**

---

## ?? Step 4: You Should See This Menu

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

## ?? Step 5: Choose an Option

### **Option 1: Run Unit Tests**
Type: `1` and press Enter

You'll see:
```
=== Event-Driven Smart Contract Platform - Test Suite ===

--- Test 1: Adding Patient Data Events ---
? Event 1: Patient Register 12345
  Hash: a1b2c3d4ef567890...
  Prev Hash: 0

--- Test 2: Validating Events ---
? Event valid: Patient Register 12345 - true
...

=== Summary ===
Total Events: 3
Chain Status: ? Valid
Platform Status: ? Ready for distributed network
```

### **Option 2: View System Information**
Type: `2` and press Enter

You'll see system architecture and port information.

### **Option 3: Exit**
Type: `3` and press Enter

Closes the program.

---

## ?? Success! You're Running EDSC

**If you see the output above with ? checkmarks**, everything is working!

---

## ?? Now What?

### **Want to understand the system?**
? Open and read: `README.md`

### **Want to see more code?**
? Open files in: `src/com/company/`
- `Main.java` - The main program
- `EDSC.java` - The blockchain
- `Broker.java` - The message broker

### **Want to modify it?**
? Edit the Java files, then recompile:
```bash
javac -d out/production/blockchainProjekt src/com/company/*.java
```

---

## ?? Something Went Wrong?

### **Error: "Java is not recognized"**
- Java not installed
- Download from: https://www.java.com/en/download/
- Restart computer after installing
- Try `java -version` again

### **Error: "Cannot find file"**
- Make sure you're in: `D:\blockchainProjekt`
- Type: `dir` to see files
- If files missing, extract project properly

### **Error: "Port already in use"**
- Another program using port 8888 or 9999
- Option 1: Restart computer
- Option 2: Edit `Main.java` and change port numbers

### **Error: "Compilation failed"**
- Check spelling of files in `src/com/company/`
- Make sure all Java files exist
- Try again from scratch:
```bash
javac -d out/production/blockchainProjekt src/com/company/*.java
```

### **No Output / Blank Screen**
- Wait 5-10 seconds (system starting)
- Check if a menu should appear
- Press `Enter` if stuck

---

## ?? Quick Reference

| What I Want | Command |
|------------|---------|
| Compile code | `javac -d out/production/blockchainProjekt src/com/company/*.java` |
| Run system | `java -cp out/production/blockchainProjekt com.company.Main` |
| Run tests | `java -cp out/production/blockchainProjekt com.company.EDSCTest` |
| Check Java | `java -version` |
| Show files | `dir` (Windows) or `ls` (Mac/Linux) |
| Change folder | `cd path/to/folder` |

---

## ?? What the System Does

```
Real Hospitals (Hospital 1 & 2)
         ?
    Admit Patients
         ?
   Send to Broker
         ?
Broker (Manages Patients)
         ?
   Broadcasts Data
         ?
All Hospitals Receive Data
         ?
EDSC Blockchain Records It
         ?
   Data is Permanent ?
```

---

## ? Success Indicators

**You know it's working when you see:**
- ? Hospital 1 & 2 started
- ? Patients admitted (P001, P002, etc.)
- ? Events added to blockchain
- ? Patient data broadcasted
- ? No error messages

---

## ?? Next Steps

1. ? You've successfully run EDSC
2. ?? Read `README.md` for full documentation
3. ?? Try modifying the code
4. ?? Run the unit tests
5. ?? Understand the architecture

---

## ?? Still Stuck?

1. Check the error message carefully
2. Try the troubleshooting section above
3. Read `README.md` for detailed help
4. Make sure Java is installed (`java -version`)
5. Make sure you're in the right folder (`dir` to check)

---

## ?? Congratulations! ??

You just ran a **Blockchain Patient Data Management System** with:
- ?? Event-Driven Architecture
- ?? Blockchain Technology
- ?? Multiple Hospitals
- ?? Patient Management
- ?? Data Immutability

**Great job! ??**

---

**Any questions? Check README.md for complete documentation.**

---

**Last Updated: 2024**
**Status: ? Fully Working**
