package Chapter03.Sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args){
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     * */
    public static void merge(int[] arr,  int left, int mid, int right, int[] temp){
        int i = left; //左边有序序列的初始索引
        int j = mid + 1; //右边有序序列的初始索引
        int k = 0; //指向temp数组的当前索引

        //先把左右两边的数据按照规则填充到temp数组，直到左右两边的有序数列有一边处理完毕为止
        while(i <= mid && j <= right){
            //如果发现左边有序序列的当前元素小于或者等于右边有序数列的当前元素，则将左边有序序列当前元素拷贝到temp数组中去
            if(arr[i] <= arr[j]){
                temp[k] = arr[i];
                k++;
                i++;
            }
            else{
                temp[k] = arr[j];
                k++;
                j++;
            }
        }

        //把有剩余数据的一边的数据依次填充到temp数组中去
        while(i <= mid){ //左边有序序列还有剩余
            temp[k] = arr[i];
            k++;
            i++;
        }
        while(j <= right){
            temp[k] = arr[j];
            k++;
            j++;
        }

        //将temp数组中的数据拷贝到arr
        k = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[k];
            k++;
            tempLeft++;
        }
    }
}
