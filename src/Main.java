import java.util.Scanner;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String inputFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\input.txt";
        String outputFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\output.txt";
        String outputZipFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\outputFromZip.txt";
        ProcessingTxtFile ptx = new ProcessingTxtFile(inputFileName, outputFileName);
        ptx.WriteToFile();

        String wayName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\out.zip";
        ArchivingAndUnarchivingZip au = new ArchivingAndUnarchivingZip();
        au.Archiving(inputFileName, wayName);
        au.Unarchiving(wayName, outputZipFileName);

        String encryptedFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\encryptedOutput.txt";
        String decryptedFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\deryptedOutput.txt";
        EncryptGen eg = new EncryptGen();
        eg.EncryptFile(outputFileName, encryptedFileName);
        eg.DecryptFile(encryptedFileName, decryptedFileName);

        String inputXmlFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\input.xml";
        String outputXmlFileName = "C:\\Users\\sivog\\IdeaProjects\\PP\\src\\output.xml";
        ProcessingXmlFile parse = new ProcessingXmlFile(inputXmlFileName, outputXmlFileName);
        parse.WriteXML();
    }

}