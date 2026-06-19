package Chapter03.Sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序时间复杂度
     * 最好情况：O(n log n)
     * 平均情况：O(n log n)
     * 最坏情况：O(n^2)
     * */
    public static void quickSort(int[] arr, int left, int right){
        int pivot = arr[(left +  right) / 2];
        int l = left;
        int r = right;
        int temp = 0;
        while (l < r) {
            //在pivot左边一直找，找到一个大于pivot的值才退出
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot右边一直找，找到一个小于pivot的值才退出
            while (arr[r] > pivot) {
                r--;
            }

            if(l >= r)
            {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现arr[l] == pivot
            if(arr[l] == pivot)
            {
                r--;
            }

            if(arr[r] == pivot)
            {
                l++;
            }
        }

        //如果l == r，必须l++, r--, 否则栈溢出
        if(l == r)
        {
            l++;
            r--;
        }
        if(left < r)
        {
            quickSort(arr, left, r);
        }

        if(right > l)
        {
            quickSort(arr, l, right);
        }
    }
}
