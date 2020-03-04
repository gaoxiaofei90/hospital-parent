set b=%cd%

del /s "*.jar"
del /s "*.war" 

TortoiseProc.exe/command:update /path:"%b%" /notempfile /closeonend:4
 
call mvn clean install -Dmaven.test.skip=true  -Pproduct

Pause
