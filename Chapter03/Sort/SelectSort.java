package Chapter03.Sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
    }

    //选择排序的时间复杂度为O(n^2)
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if(min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值与arr[i]交换
            if(minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.printf("The %d time sorting array is : ", i + 1);
            System.out.println(Arrays.toString(arr));
        }
    }
}
