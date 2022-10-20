import java.io.*;
import java.util.Stack;

public class ProcessingTxtFile {
    private static String inputFileName;
    private static String outputFileName;
    public ProcessingTxtFile(String inputFName, String outputFName) {
        inputFileName = inputFName;
        outputFileName = outputFName;
    }

    public static int GetPriority(char token) {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == ')') return -1;
        else if (token == '(') return 1;
        else return 0;
    }

    public static String ReadFromFile() throws IOException {
        FileReader fr = new FileReader(inputFileName);
        BufferedReader reader = new BufferedReader(fr);
        return reader.readLine();
    }

    public static String ExpressionToRPN() throws IOException {
        String expr = ReadFromFile();
        String current = "";
        Stack<Character> stack = new Stack<Character>();

        int priority;
        for (int i = 0; i < expr.length(); i++) {
            priority = GetPriority(expr.charAt(i));
            if (priority == 0) {
                current += expr.charAt(i);
            }
            else if (priority == 1) {
                stack.push(expr.charAt(i));
            }
            else if (priority == -1) {
                current += " ";
                while (GetPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop();
            }
            else {
                current += " ";
                while(!stack.empty()) {
                    if (GetPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(expr.charAt(i));
            }

        }

        while (!stack.empty()) {
            current += stack.pop();
        }

        return current;
    }

    public static Double RPNToAnswer() throws IOException {
        String rpn = ExpressionToRPN();
        String operand = new String();
        Stack<Double> stack_ = new Stack<Double>();
        for (int i = 0 ;i < rpn.length(); i++) {

            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (GetPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && GetPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        i--;
                        break;
                    }
                }
                stack_.push(Double.parseDouble(operand));
                operand = new String();
            }
            if (GetPriority(rpn.charAt(i)) > 1) {
                double a = stack_.pop();
                double b = stack_.pop();
                if (rpn.charAt(i) == '+') stack_.push( b + a);
                if (rpn.charAt(i) == '-') stack_.push(b - a);
                if (rpn.charAt(i) == '*') stack_.push(b * a);
                if (rpn.charAt(i) == '/') stack_.push(b / a);
            }
        }
        return stack_.pop();
    }

    public static void WriteToFile() throws IOException {
        FileWriter fw = new FileWriter(outputFileName);
        fw.write(Double.toString(RPNToAnswer()));
        fw.flush();
    }

}
