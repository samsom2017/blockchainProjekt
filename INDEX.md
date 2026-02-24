# ?? EDSC Blockchain Platform - Complete Setup Guide

## ? **TL;DR (Too Long; Didn't Read)**

### **Just Want to Run It?**

**Windows:**
```bash
Double-click: start.bat
```

**Command Line (Any OS):**
```bash
cd D:\blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
java -cp out/production/blockchainProjekt com.company.Main
```

---

## ?? Documentation Files

| File | What It Contains |
|------|-----------------|
| **QUICKSTART.md** | ? **START HERE** - 5-minute quick start guide |
| **README.md** | Full documentation with architecture & troubleshooting |
| **This File** | Navigation guide for all documentation |

---

## ?? Choose Your Path

### ?? **"I'm in a hurry"**
? Open **QUICKSTART.md** and follow the 3 easy options

### ????? **"I want to understand the system"**
? Read **README.md** for full architecture and features

### ?? **"I want detailed technical info"**
? Read both README.md and QUICKSTART.md

---

## ?? 3 Ways to Start (Pick One!)

### **Option 1: Windows Batch (Easiest)**
```
Double-click: start.bat
```

### **Option 2: Command Line**
```bash
cd D:\blockchainProjekt
javac -d out/production/blockchainProjekt src/com/company/*.java
java -cp out/production/blockchainProjekt com.company.Main
```

### **Option 3: IDE (IntelliJ/Eclipse)**
1. Open project
2. Right-click Main.java
3. Click "Run Main.main()"

---

## ?? Project Structure

```
blockchainProjekt/
?
??? ?? QUICKSTART.md          ? **START HERE** (5 min read)
??? ?? README.md              ? Full documentation
??? ?? INDEX.md               ? This file
?
??? ?? start.bat              ? Windows launcher
??? ?? start.ps1              ? PowerShell launcher
??? ?? start.sh               ? Linux/Mac launcher
?
??? src/com/company/
?   ??? ?? Main.java          ? Main entry point
?   ??? ?? EDSC.java           ? Blockchain engine
?   ??? ?? BlockchainEvent.java ? Event class
?   ??? ?? Broker.java         ? Message broker
?   ??? ?? Hospital.java       ? Hospital system
?   ??? ?? EDSCTest.java       ? Unit tests
?
??? out/production/blockchainProjekt/
    ??? (Compiled .class files)
```

---

## ?? System Overview

**What does this system do?**

```
Patient Data Management System using Blockchain

???????????????????????????????????????????????????????
?  Broker (Port 8888)                                 ?
?  - Manages patient-hospital relationships           ?
?  - Broadcasts vital signs data                      ?
???????????????????????????????????????????????????????
              ?           ?
    ?????????????????????
    ?                   ?
Hospital 1          Hospital 2
(Admits patients)   (Admits patients)
    ?                   ?
    ?????????????????????
              ?
???????????????????????????????????????????????????????
?  EDSC (Port 9999)                                   ?
?  - Immutable blockchain for all events              ?
?  - SHA-256 hashing for security                     ?
?  - Event validation and chaining                    ?
???????????????????????????????????????????????????????
```

---

## ?? Test Options

### **Full System Test**
```bash
java -cp out/production/blockchainProjekt com.company.Main
# Then select "Option 1"
```

### **Unit Tests Only**
```bash
java -cp out/production/blockchainProjekt com.company.EDSCTest
```

---

## ? Verification Checklist

- [ ] Java is installed (`java -version` works)
- [ ] Can navigate to project folder
- [ ] Can compile code (no errors)
- [ ] Can run Main.java or EDSCTest.java
- [ ] Sees expected output with patient data
- [ ] Blockchain events appear in output

---

## ??? Common Commands

```bash
# Compile all code
javac -d out/production/blockchainProjekt src/com/company/*.java

# Run full system
java -cp out/production/blockchainProjekt com.company.Main

# Run tests
java -cp out/production/blockchainProjekt com.company.EDSCTest

# Check Java
java -version

# Navigate to project
cd D:\blockchainProjekt
```

---

## ?? Next Steps

1. **Read QUICKSTART.md** (5 minutes)
2. **Choose a launch method**
3. **Run the system**
4. **Try the menu options**
5. **Read README.md** for details (optional)

---

## ?? Quick Troubleshooting

| Problem | Solution |
|---------|----------|
| Java not found | Install Java 8+ from java.com |
| Port already in use | Close other Java apps or change ports in Main.java |
| Compilation errors | Make sure all files are in src/com/company/ |
| No output | Check terminal window for error messages |

---

## ?? Need Help?

1. **Quick answer?** ? Check QUICKSTART.md (Common Q&A section)
2. **Detailed answer?** ? Check README.md (Troubleshooting section)
3. **Code question?** ? Check the code comments in Java files

---

## ?? You're Ready!

**Open QUICKSTART.md and follow the 3 easy steps!**

---

**Happy coding! ??**
