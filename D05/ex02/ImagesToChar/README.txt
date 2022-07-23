# Compilation:

1. mkdir target

2. javac -cp ".:./lib/JColor-5.0.0.jar:./lib/jcommander-1.78.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

3. cp -rf ./src/resources ./target

4. cd target ; jar xf ../lib/JColor-5.0.0.jar com ; jar xf ../lib/jcommander-1.78.jar com ; cd ..

5. jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#Execution:
java -cp ./target edu.school21.printer.app.Program --white=RED --black=GREEN
