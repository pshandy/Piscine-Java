package edu.school21.preprocessor;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String preProcess(String initString) {
        return (initString.toLowerCase());
    }

}
