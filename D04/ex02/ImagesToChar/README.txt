# Compilation:

1. mkdir lib target

2. curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar

3. curl -o lib/JColor-5.5.1.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar

4. javac -cp ".:./lib/JColor-5.5.1.jar:./lib/jcommander-1.82.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

5. cp -rf ./src/resources ./target

6. cd target ; jar xf ../lib/JColor-5.5.1.jar com ; jar xf ../lib/jcommander-1.82.jar com ; cd ..

7. jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#Execution:
java -cp ./target edu.school21.printer.app.Program --white=RED --black=GREEN
