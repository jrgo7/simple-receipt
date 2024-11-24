del /s /q *.class
javac -cp .;lib/* "src/controller/Driver.java"
java -cp .;lib/* "src/controller/Driver"