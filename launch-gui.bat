@echo off
setlocal enabledelayedexpansion
cd /d "%~dp0"
start javaw.exe library.LibraryAppGUI
exit /b 0
