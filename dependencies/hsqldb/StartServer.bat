@set JAR=./hsqldb.jar

@set CLS=org.hsqldb.Server
@set DBPATH=data/demo
@set DBNAME=demo

java -cp %JAR% %CLS% -database.0 %DBPATH% -dbname.0 %DBNAME%
@goto end

	