@echo off
title Servidor Backend - Urban Look (Spring Boot)
color 0E
echo ====================================================================
echo        URBAN LOOK - INICIANDO BACKEND (SPRING BOOT + MYSQL)
echo ====================================================================
echo.
echo Verificando directorio del backend...
cd /d "%~dp0peluqueria-backend"

echo.
echo Arrancando aplicacion en http://localhost:8080 ...
echo (Asegurate de tener MySQL de XAMPP encendido)
echo.

"C:\Users\Usuario\.m2\wrapper\dists\apache-maven-3.9.16\0daed3be3ebd1c706f0e69e8b07c6b73f5cc4ea3dfce72a8d0ec2e849ca2ddb0\bin\mvn.cmd" spring-boot:run

pause

