import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String inputFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\input.txt";
        String outputFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\output.txt";
        ProcessingTxtFile ptx = new ProcessingTxtFile(inputFileName, outputFileName);
        ptx.WriteToFile();

        JavaReadXml parse = new JavaReadXml();
        parse.viewXML("C:\\Users\\sivog\\IdeaProjects\\PP\\src\\person.xml");
    }

}