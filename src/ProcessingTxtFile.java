import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessingTxtFile {
    private static String inputFileName;
    private static String outputFileName;
    public ProcessingTxtFile(String inputFName, String outputFName) {
        inputFileName = inputFName;
        outputFileName = outputFName;
    }

    public static String ReadFromFile() throws IOException {
        FileReader fr = new FileReader(inputFileName);
        BufferedReader reader = new BufferedReader(fr);
        return reader.readLine();
    }
    public static void WriteToFile() throws IOException {
        FileWriter fw = new FileWriter(outputFileName);
        RPN rpn = new RPN(ReadFromFile());
        fw.write(Double.toString(rpn.RPNToAnswer()));
        fw.flush();
    }

}
