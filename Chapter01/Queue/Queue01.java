package Chapter01.Queue;

import java.util.Scanner;

public class Queue01 {
    /*
    * 队列是一个有序列表，可以用数组或是链表来实现。
    * 遵循先入先出的原则。即:先存入队列的数据，要先取出。后存入的要后取
    * */
    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(10);
        char key = ' ';
        Scanner console = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println("s(show)");
            System.out.println("a(add)");
            System.out.println("r(remove)");
            System.out.println("h(head)");
            System.out.println("e(exit)");
            System.out.print("Enter a character: ");
            key = console.next().charAt(0);
            switch(key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    flag = false;
                    break;
                case 'a':
                    System.out.println("Enter a number: ");
                    int number = console.nextInt();
                    queue.addQueue(number);
                    break;
                case 'r':
                    queue.removeQueue();
                    break;
                case 'h':
                    int num = queue.headQueue();
                    System.out.println(num);
            }
        }
    }
}

class ArrayQueue{
    private int maxsize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxsize){
        this.maxsize = maxsize;
        //front指向队列第一个位置的前一个位置。
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxsize];
    }

    public boolean isEmpty(){
        return (front == rear);
    }

    public boolean isFull(){
        return (rear == maxsize - 1);
    }

    public void addQueue(int value){
        if(isFull()){
            System.out.println("Queue is full");
        }
        rear++;
        arr[rear] = value;
        System.out.println(value + " added to queue successfully.");
    }

    public int removeQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        front++;
        return arr[front];
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        for(int i = front + 1; i <= rear; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return arr[front + 1];
    }
}
