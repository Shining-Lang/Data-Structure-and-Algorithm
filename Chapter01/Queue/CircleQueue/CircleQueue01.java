package Chapter01.Queue.CircleQueue;

import java.util.Scanner;

public class CircleQueue01 {
    /*
    * 环形队列的有效值始终为maxsize - 1； 他有一位是需要空出来的，
    * 如果不这样做的话，rear == front就与空队列时冲突了
    * */
    public static void main(String[] args){
        ArrayCircleQueue queue = new ArrayCircleQueue(5);
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

class ArrayCircleQueue{
    private int maxsize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayCircleQueue(int maxsize){
        this.maxsize = maxsize;
        //front指向队列第一个位置。
        this.front = 0;
        //rear指向下一个插入的位置
        this.rear = 0;
        this.arr = new int[maxsize];
    }

    public boolean isEmpty(){
        return (front == rear);
    }

    public boolean isFull(){
        return ((rear + 1) % maxsize == front);
    }

    public void addQueue(int value){
        if(isFull()){
            System.out.println("Queue is full");
            return;
        }
        arr[rear] = value;
        rear = (rear + 1) % maxsize;
        System.out.println(value + " added to queue successfully.");
    }

    public int removeQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        int frontValue = arr[front];
        front =  (front + 1) % maxsize;
        return frontValue;
    }

    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        for(int i = front; i < (front + size()); i++){
            System.out.print(arr[i % maxsize] + " ");
        }
        System.out.println();
    }

    //求队列中有效元素的数量。
    public int size(){
        return (rear + maxsize - front) % maxsize;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return arr[front];
    }
}
