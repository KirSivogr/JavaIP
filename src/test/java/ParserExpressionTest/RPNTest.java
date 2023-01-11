package ParserExpressionTest;

import ParserExpression.RPN;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RPNTest {
    @Test
    public void RPNTest1() throws Exception {
        String expression = "3+21+3/3*0.5";
        Double expectedAnswer = 24.5;
        RPN rpn = new RPN(expression);
        Double RPNAnswer = rpn.RPNToAnswer();
        Assertions.assertEquals(expectedAnswer, RPNAnswer);
    }

    @Test
    public void RPNTest2() throws Exception {
        String expression = "1+3+3/3*(0.5+0.5)";
        Double expectedAnswer = 5.0;
        RPN rpn = new RPN(expression);
        Double RPNAnswer = rpn.RPNToAnswer();
        Assertions.assertEquals(expectedAnswer, RPNAnswer);
    }

    @Test
    public void RPNTest3() throws Exception {
        String expression = "0/1/1/1/1/1";
        Double expectedAnswer = 0.0;
        RPN rpn = new RPN(expression);
        Double RPNAnswer = rpn.RPNToAnswer();
        Assertions.assertEquals(expectedAnswer, RPNAnswer);
    }

    @Test
    public void RPNTest4() throws Exception {
        String expression = "3*3*(3+3)";
        Double expectedAnswer = 54.0;
        RPN rpn = new RPN(expression);
        Double RPNAnswer = rpn.RPNToAnswer();
        Assertions.assertEquals(expectedAnswer, RPNAnswer);
    }


    @Test
    public void RPNTest5() throws Exception {
        String expression = "1+3*3+2+0/5+0.5";
        Double expectedAnswer = 12.5;
        RPN rpn = new RPN(expression);
        Double RPNAnswer = rpn.RPNToAnswer();
        Assertions.assertEquals(expectedAnswer, RPNAnswer);
    }

    @Test
    public void RPNTest6() throws Exception {
        String expression = "7/0";
        Double expectedAnswer = 0.0;
        RPN rpn = new RPN(expression);
        try {
            Double RPNAnswer = rpn.RPNToAnswer();
            Assertions.assertEquals(expectedAnswer, RPNAnswer);
        } catch (ArithmeticException thrown) {
            Assertions.assertEquals("Division by zero!!!", thrown.getMessage());
        }
    }
}
