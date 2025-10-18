@echo off
REM fix_install_duplicate.bat
REM Ejecuta una serie de comandos adb para diagnosticar y tratar INSTALL_FAILED_DUPLICATE_PACKAGE

set LOG=%~dp0fix_install_duplicate_log.txt
echo --- %date% %time% --- > "%LOG%"
echo ----- adb devices ----- >> "%LOG%"
adb devices >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- busqueda de paquete (pm list packages) ----- >> "%LOG%"
adb shell pm list packages | findstr com.practica1Moviles.Ticona >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- intento de adb uninstall (app normal) ----- >> "%LOG%"
adb uninstall com.practica1Moviles.Ticona >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- intento de desinstalar para user 0 (si aplica) ----- >> "%LOG%"
adb shell pm uninstall --user 0 com.practica1Moviles.Ticona >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- listar usuarios (pm list users) ----- >> "%LOG%"
adb shell pm list users >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- listar paquetes para todos los usuarios ----- >> "%LOG%"
adb shell pm list packages --all-users | findstr com.practica1Moviles.Ticona >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo ----- reiniciar adb server ----- >> "%LOG%"
adb kill-server >> "%LOG%" 2>&1
adb start-server >> "%LOG%" 2>&1
echo. >> "%LOG%"
echo Hecho. Revisa el archivo de log: %LOG% >> "%LOG%"
echo.
echo ¿Deseas reiniciar el dispositivo/emulador ahora? (s/n)
set /p REBOOT_ANS=
if /I "%REBOOT_ANS%"=="s" (
    echo Reiniciando dispositivo... >> "%LOG%"
    adb reboot >> "%LOG%" 2>&1
    echo Dispositivo reiniciado. Espera a que el dispositivo vuelva a estar online.
) else (
    echo No se reiniciará el dispositivo.
)
echo.
echo El script no forzará más acciones. Si quieres ejecutar de nuevo el script, simplemente vuelve a correrlo.
echo.
echo Presiona una tecla para salir...
pause > nul
