@echo off
:: Install Node.js v22
echo Installing Node.js v22...
echo.
:: Download Node.js Installer (v22.x.x)
curl -o nodejs.msi https://nodejs.org/dist/v22.0.0/node-v22.0.0-x64.msi
:: Install Node.js
msiexec /i nodejs.msi /quiet /norestart
:: Clean up Node.js Installer
del nodejs.msi
echo Node.js installation complete.

:: Install Appium using npm
echo Installing Appium...
npm install -g appium
echo Appium installation complete.


:: Confirm installation
echo.
echo Installation complete. Checking versions...
node -v
npm -v
appium --version
