@echo off
SETLOCAL EnableDelayedExpansion

:: Define download URLs
set "CMDLINE_URL=https://dl.google.com/android/repository/commandlinetools-win-6609375_latest.zip"
set "PLATFORM_URL=https://dl.google.com/android/repository/platform-tools-latest-windows.zip"

:: Define the destination directory where tools will be stored
set "DEST_DIR=%USERPROFILE%\Android\Sdk"

:: Create the destination folder if it doesn't exist
if not exist "%DEST_DIR%" mkdir "%DEST_DIR%"

:: Download the CMDLine tools
echo Downloading Android CMDLine Tools...
powershell -Command "(New-Object Net.WebClient).DownloadFile('%CMDLINE_URL%', '%DEST_DIR%\cmdline-tools.zip')"

:: Download the Platform tools
echo Downloading Android Platform Tools...
powershell -Command "(New-Object Net.WebClient).DownloadFile('%PLATFORM_URL%', '%DEST_DIR%\platform-tools.zip')"

:: Extract the CMDLine tools and Platform tools
echo Extracting CMDLine tools...
powershell -Command "Expand-Archive -Path '%DEST_DIR%\cmdline-tools.zip' -DestinationPath '%DEST_DIR%'"

echo Extracting Platform tools...
powershell -Command "Expand-Archive -Path '%DEST_DIR%\platform-tools.zip' -DestinationPath '%DEST_DIR%'"

:: Delete the zip files after extraction
del "%DEST_DIR%\cmdline-tools.zip"
del "%DEST_DIR%\platform-tools.zip"

:: Set environment variables for the current session
setx ANDROID_HOME "%DEST_DIR%"
setx PATH "%PATH%;%DEST_DIR%\cmdline-tools\bin;%DEST_DIR%\platform-tools"

:: Display the results
echo Android tools have been downloaded and environment variables set.
echo You may need to restart your terminal or system for changes to take effect.

:: Pause to let the user see the message before the script exits
pause
