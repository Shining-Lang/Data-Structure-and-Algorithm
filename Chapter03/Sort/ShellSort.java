package Chapter03.Sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println();
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
    }

    /**
     * 方式             平均时间复杂度             最坏时间复杂度    实际效率
     * 交换式希尔排序    约 O(n^1.3 ~ n^2)         O(n^2)           较慢
     * 移位法希尔排序    约 O(n^1.3 ~ n^2)         O(n^2)           较快
     * */
    //交换式的希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < arr.length; i++) {
                for(int j = i - gap; j >= 0; j -= gap) {
                    if(arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                    else {
                        break;
                    }
                }
            }
            System.out.printf("The %d time sorting array is : ", ++count);
            System.out.println(Arrays.toString(arr));
        }
    }

    //移位法的希尔排序
    public static void shellSort2(int[] arr) {
        int round = 0;

        for(int step = arr.length / 2; step > 0; step /= 2) {
            for(int currentIndex = step; currentIndex < arr.length; currentIndex++) {
                int insertIndex = currentIndex;
                int insertValue = arr[insertIndex];

                while(insertIndex - step >= 0 && arr[insertIndex - step] > insertValue) {
                    arr[insertIndex] = arr[insertIndex - step];
                    insertIndex -= step;
                }

                if(insertIndex != currentIndex) {
                    arr[insertIndex] = insertValue;
                }
            }

            System.out.printf("The %d time sorting array is : ", ++round);
            System.out.println(Arrays.toString(arr));
        }
    }

}
