@set JAR=./hsqldb.jar

@set CLS=org.hsqldb.util.DatabaseManagerSwing
@set DRV=org.hsqldb.jdbcDriver
@set URL=jdbc:hsqldb:hsql://localhost/demo
@set USR=sa

java -cp %JAR% %CLS% -driver %DRV% -url %URL% -user %USR%
@goto end


