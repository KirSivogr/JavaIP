package processors;

import ParserExpression.RPN;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessingTxtFile {
    private static String inputFileName;
    public ProcessingTxtFile(String inputFName) {
        inputFileName = inputFName;
    }

    public static List<String> readFromFile() throws IOException {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new FileReader(inputFileName));
        while(scanner.hasNextLine()) {
            String string;
            string = scanner.nextLine() + "\n";
            result.add(string);
        }
        scanner.close();
        return result;
    }
    public static void writeToFile(String outputFileName) throws Exception {
        FileWriter fw = new FileWriter(outputFileName);
        List<String> dataFromFile = readFromFile();
        for (int i = 0; i < dataFromFile.size(); i++) {
            RPN rpn = new RPN(dataFromFile.get(i));
            fw.write(Double.toString(rpn.RPNToAnswer()) + '\n');
        }
        fw.flush();
    }

}
