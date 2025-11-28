@echo off
echo.
echo ========================================
echo Find Your Computer IP Address
echo ========================================
echo.
ipconfig | findstr /i "IPv4"
echo.
echo ========================================
echo Use the IPv4 Address above (e.g., 192.168.x.x)
echo Open in your phone browser:
echo http://192.168.1.61:5500
echo ========================================
echo.
pause
