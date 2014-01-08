README

To run the program…
1) From the command line on Windows or Linux, navigate to the directory where the code files are stored.
2) To compile the code, compile all files as follows:
	javac *.java
3) To run the program, use the following command to run the program on the JVM:
	java Run surnames.txt Smith Jones Winton
   This would run the program using the surnames.txt file and would find matches for the surnames “Smith”, “Jones” and “Winton”.

To run the unit tests…
Note: These Unit Tests use JUnit 4.6. The program does not rely on JUnit to work but the Unit tests however do.
1) Copy the unit tests from the “tests” directory into the directory where the code for the program is located.
2) Make sure the JUnit.jar file is on your class path.
3) Compile all files as follows:
	javac *.java
4) To run the TestIOManager:
	java org.junit.runner.JUnitCore TestIOManager
5) To run the TestMatching:
	java org.junit.runner.JUnitCore TestMatching