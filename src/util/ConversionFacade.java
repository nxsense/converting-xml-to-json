package util;


public class ConversionFacade {
    private final UserInterface userInterface;

    public ConversionFacade() {
        this.userInterface = new UserInterface();
    }

    public void convert() {
        userInterface.printAvailableFormats();
        userInterface.promptInputFormat();
        int inputFormat = userInterface.getInputFormat();
        userInterface.promptOutputFormat();
        int outputFormat = userInterface.getOutputFormat();
        userInterface.promptInputFilePath();
        String inputFilePath = userInterface.getInputFile();
        userInterface.promptOutputFilePath();
        String outputFilePath = userInterface.getOutputFile();
        userInterface.convertFiles(inputFormat, outputFormat, inputFilePath, outputFilePath);
    }
}
