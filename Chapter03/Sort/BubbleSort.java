package Chapter03.Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        bubbleSort(arr);
    }

    //冒泡排序时间复杂度为O(n^2)
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false; //表示是否进行过交换

        //外层for loop是一共进行arr.length趟， 每次都确定最后一个位置的值
        for (int i = 0; i < arr.length - 1; i++) {
            //内层for loop是每趟里面两两比较
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果前面的数比后面的数大就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            System.out.printf("The %d time sorting array is : ", i + 1);
            System.out.println(Arrays.toString(arr));

            if (!flag) {
                System.out.println("The array has been ordered.");
                break; //没有交换，那就说明已经排序好了，无需进行之后的排序，退出循环
            }
            else  {
                flag = false; //重置进行下次交换
            }
        }
    }
}
