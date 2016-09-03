@ECHO OFF
cd /Users/mohan/Documents/AI/src/main/java/csula/cs4660/exercises
ECHO Compiling %~nx1...
IF EXIST %~n1.class (
DEL %~n1.class
)
javac %~nx1
IF EXIST %~n1.class (
	ECHO Running %~n1...
	ECHO -----------------------------------------------
	java %~n1
	ECHO -----------------------------------------------
)