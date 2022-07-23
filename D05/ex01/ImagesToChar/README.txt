# Compilation:
mkdir target ; javac -d ./target/ src/java/edu/school21/printer/*/*.java ; cp -rf ./src/resources ./target

# Jar Creation:
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#Execution:
java -cp ./target edu.school21.printer.app.Program

#Example
java -cp ./target edu.school21.printer.app.Program . 0
