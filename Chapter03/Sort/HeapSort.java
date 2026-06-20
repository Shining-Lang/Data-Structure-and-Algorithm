package Chapter03.Sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6 ,8 , 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int temp;

        // 1. 构建大顶堆
        for(int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 2. 将堆顶元素和末尾元素交换，再调整堆
        for(int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组调整成一个大顶堆

    /**
     * 将以index对应的非叶子节点的树调整成大顶堆
     * @param arr 表示待调整的数组
     * @param index 表示非叶子节点在数组中的索引
     * @param length 表示多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int index, int length){
        int temp = arr[index];

        for(int i = index * 2 + 1; i < length; i = i * 2 + 1){
            //看一看该叶子节点的左子节点和右子节点哪个大
            if(i + 1 < length && arr[i] < arr[i + 1]){
                i++;
            }
            //如果子节点大于父节点
            if(arr[i] > temp){
                //把值赋给当前叶子节点
                arr[index] = arr[i];
                index = i;
            }
            else{
                break;
            }

            arr[index] = temp;
        }
    }
}
