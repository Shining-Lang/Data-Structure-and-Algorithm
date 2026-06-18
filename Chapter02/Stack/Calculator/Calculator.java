package Chapter02.Stack.Calculator;

public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-20";

        ArrayStack numberStack = new ArrayStack(10);
        ArrayStack operatorStack = new ArrayStack(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int result = 0;
        char ch = ' ';
        String keepNum = "";

        while(index < expression.length())
        {
            ch = expression.charAt(index);
            if(operatorStack.isOperator(ch))
            {
                if(!operatorStack.isEmpty())
                {
                    if(operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek()))
                    {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operator = operatorStack.pop();
                        result = numberStack.calculate(num1, num2, operator);
                        numberStack.push(result);
                        operatorStack.push(ch);
                    }
                    else
                    {
                        operatorStack.push(ch);
                    }
                }
                else
                {
                    operatorStack.push(ch);
                }
            }
            else
            {
//                numberStack.push(Integer.parseInt(String.valueOf(ch)));
                //当处理多位数时，要接着再扫描一位；
                keepNum += ch;

                if(index == expression.length() - 1)
                {
                    numberStack.push(Integer.parseInt(keepNum));
                }
                else
                {
                    if(operatorStack.isOperator(expression.charAt(index + 1)))
                    {
                        numberStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        }

        while(!operatorStack.isEmpty())
        {
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operator = operatorStack.pop();
            result = numberStack.calculate(num1, num2, operator);
            numberStack.push(result);
        }

        System.out.printf("%s = %d\n", expression,  numberStack.pop());
    }
}

class ArrayStack
{
    private int maxSize;
    private int[] stack;
    private int top;

    public ArrayStack(int maxSize)
    {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    //栈满
    public boolean isFull()
    {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty()
    {
        return (top == -1);
    }

    //入栈(push)
    public void push(int value)
    {
        if(isFull())
        {
            System.out.println("Stack is full");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈(pop)
    public int pop()
    {
        if(isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //返回当前栈顶的值
    public int peek()
    {
        if(isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        return stack[top];
    }

    //显示栈的情况
    public void list()
    {
        if(isEmpty())
        {
            System.out.println("Stack is empty");
            return;
        }
        for (int i = top; i >= 0; i--)
        {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    //返回运算符的优先级
    public int priority(int operator)
    {
        if(operator == '*' || operator == '/')
        {
            return 1;
        }
        else if(operator == '+' || operator == '-')
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public boolean isOperator(int value)
    {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    public int calculate(int num1, int num2, int operator)
    {
        int result = 0; //存放计算的结果

        switch(operator)
        {
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            default:
                break;
        }
        return result;
    }

}
