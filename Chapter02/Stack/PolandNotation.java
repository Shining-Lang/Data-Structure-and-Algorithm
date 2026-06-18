package Chapter02.Stack;

import java.util.ArrayList;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        String expression = "1.5+((2.5+3.5)*4)-5";
        ArrayList<String> list = toInfixExpressionList(expression);
        ArrayList<String> suffixExpression = parseSuffixExpressionList(list);
        System.out.println(suffixExpression);
        double result = calculate(suffixExpression);
        System.out.println(result);
    }

    //将中缀表达式转成对应的ArrayList
    public static ArrayList<String> toInfixExpressionList(String expression) {
        ArrayList<String> result = new ArrayList<>();
        int i = 0; //用于遍历中缀表达式字符串
        String keepNum; //对多位数的拼接
        char ch; //每遍历到一个字符就放入到ch中
        do {
            ch = expression.charAt(i);
            //如果ch是一个非数字，就加入到result
            if(ch < '0' || ch > '9')
            {
                result.add(String.valueOf(ch));
                i++;
            }
            else
            {
                keepNum = "";
                while(i < expression.length() && ((ch = expression.charAt(i)) >= '0' && (ch = expression.charAt(i)) <= '9' || ch == '.'))
                {
                    keepNum += ch;
                    i++;
                }
                result.add(keepNum);
            }
        }while(i < expression.length());

        return result;
    }

    public static ArrayList<String> parseSuffixExpressionList(ArrayList<String> list)
    {
        //定义两个栈
        Stack<String> stack = new Stack<>();
        //因为栈s2在整个转换过程中，并没有pop操作，所以直接使用ArrayList来表示s2
        ArrayList<String> result = new ArrayList<>();
        for(String element : list)
        {
            //如果是数字就加入到result
            if(element.matches("\\d+(\\.\\d+)?"))
            {
                result.add(element);
            }
            else if(element.equals("("))
            {
                stack.push(element);
            }
            else if(element.equals(")"))
            {
                //如果遇到了")", 那么则依次弹出S1的栈顶的运算符，直到遇到了"("为止，此时这对括号将被丢弃
                while(!stack.peek().equals("("))
                {
                    result.add(stack.pop());
                }
                stack.pop();
            }
            else
            {
                //当element的优先级小于s1栈顶运算符的优先级，将s1栈顶的运算符pop，然后押入到result中去，然后再返回比较。
                while(!stack.isEmpty() && !stack.peek().equals("(") && Operation.getValue(stack.peek()) >= Operation.getValue(element))
                {
                    result.add(stack.pop());
                }
                stack.push(element);
            }
        }
        //将s1中剩余的运算符依次pop然后加入到result中
        while (!stack.isEmpty())
        {
            result.add(stack.pop());
        }

        return result;
    }

    //将逆波兰表达式依次将数据和运算符放入到一个ArrayList中
    public static ArrayList<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String element : s)
        {
            list.add(element);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    public static double calculate(ArrayList<String> list)
    {
        Stack<String> stack = new Stack<>();
        for(String element : list)
        {
            if(element.matches("\\d+(\\.\\d+)?"))
            {
                //入栈
                stack.push(element);
            }
            else
            {
                //pop出两个数并运算
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                double result = -1;
                if(element.equals("+"))
                {
                    result = num1 + num2;
                }
                else if(element.equals("-"))
                {
                    result = num1 - num2;
                }
                else if(element.equals("*"))
                {
                    result = num1 * num2;
                }
                else if (element.equals("/"))
                {
                    result = num1 / num2;
                }
                else
                {
                    throw new RuntimeException("Invalid operation.");
                }
                stack.push(String.valueOf(result));
            }
        }
        return Double.parseDouble(stack.pop());
    }
}

//编写一个类Operation，可以返回一个运算符的对应的优先级
class Operation
{
    private static final int ADDITION = 1;
    private static final int SUBTRACTION = 1;
    private static final int MULTIPLICATION = 2;
    private static final int DIVISION = 2;

    public static int getValue(String operation)
    {
        int result = 0;
        switch(operation)
        {
            case "+":
                result = ADDITION;
                break;
            case  "-":
                result = SUBTRACTION;
                break;
            case "*":
                result = MULTIPLICATION;
                break;
            case  "/":
                result = DIVISION;
                break;
            default:
                System.out.println("Invalid operation.");
                break;
        }
        return result;
    }

}