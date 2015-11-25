set ProjectPath=C:\Users\Sampath\workspace\Fullfurnish
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\Lib\*
echo %classpath%
java org.testng.TestNG %ProjectPath%\testsuite.xml
pause