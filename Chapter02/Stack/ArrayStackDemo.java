package Chapter02.Stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args)
    {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while(flag)
        {
            System.out.println("show stack");
            System.out.println("exit stack");
            System.out.println("push value");
            System.out.println("pop value");
            System.out.print("Enter the choice: ");
            key = scanner.next();
            switch (key)
            {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.print("Enter the value: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try
                    {
                        int result = stack.pop();
                        System.out.println(result);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        System.out.println("End the program.");
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
}