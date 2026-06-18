package Chapter02.Stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();

        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while(flag) {
            System.out.println("show stack");
            System.out.println("exit stack");
            System.out.println("push value");
            System.out.println("pop value");
            System.out.print("Enter the choice: ");

            key = scanner.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.print("Enter the value: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.println(result);
                    } catch(Exception e) {
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

class LinkedListStack {
    private StackNode top;

    public LinkedListStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int value) {
        StackNode node = new StackNode(value);
        node.next = top;
        top = node;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        int value = top.value;
        top = top.next;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        StackNode temp = top;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

class StackNode {
    public int value;
    public StackNode next;

    public StackNode(int value) {
        this.value = value;
    }
}


