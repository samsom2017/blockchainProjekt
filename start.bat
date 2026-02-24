@echo off
REM Quick Start Script for EDSC Blockchain Platform
REM Run this script to compile and start the system

setlocal enabledelayedexpansion

echo.
echo ====================================================================
echo   EDSC Blockchain Platform - Quick Start Script
echo ====================================================================
echo.

REM Step 1: Check if Java is installed
echo [1/4] Checking Java installation...
java -version >nul 2>&1
if %errorlevel% equ 0 (
    echo + Java is installed
    java -version 2>&1 | findstr /I "version"
) else (
    echo - Java is NOT installed
    echo   Please install Java 8 or higher and try again
    pause
    exit /b 1
)

echo.

REM Step 2: Compile the code
echo [2/4] Compiling source code...

if exist "out\production\blockchainProjekt" (
    echo   Cleaning previous build...
    rmdir /s /q "out\production\blockchainProjekt" >nul 2>&1
)

mkdir "out\production\blockchainProjekt" >nul 2>&1

javac -d out/production/blockchainProjekt src/com/company/*.java
if %errorlevel% equ 0 (
    echo + Compilation successful!
) else (
    echo - Compilation failed!
    echo   Check the error messages above and try again
    pause
    exit /b 1
)

echo.

REM Step 3: Show menu
echo [3/4] Preparing to launch...
echo.
echo ====================================================================
echo            Launch Options
echo ====================================================================
echo   1. Run Full System (Main.java)
echo   2. Run Unit Tests (EDSCTest.java)
echo   3. Exit
echo ====================================================================
echo.

set /p choice="Select option (1-3): "

echo.

REM Step 4: Execute selected option
echo [4/4] Launching application...
echo.

if "%choice%"=="1" (
    echo Starting EDSC Full System...
    echo ====================================================================
    echo.
    java -cp out/production/blockchainProjekt com.company.Main
    goto end
)

if "%choice%"=="2" (
    echo Running Unit Tests...
    echo ====================================================================
    echo.
    java -cp out/production/blockchainProjekt com.company.EDSCTest
    goto end
)

if "%choice%"=="3" (
    echo Exiting...
    exit /b 0
)

echo - Invalid option. Please select 1, 2, or 3.
pause
exit /b 1

:end
echo.
echo + Program completed successfully!
pause
