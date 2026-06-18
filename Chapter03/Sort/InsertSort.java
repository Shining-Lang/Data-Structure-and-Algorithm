package Chapter03.Sort;

import java.util.Arrays;

public class InsertSort {
    public static void  main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }

    //插入排序， 时间复杂度为O(n^2)
    //插入排序可以想象成，将一个数组分成两半，第一次前一半只有默认的第一个数据，然后用后一半的第一个数据去跟前一半从尾到头一次比较，看看插入到哪里
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i]; //取到后一半的第一个数
            int lastIndex = i - 1; //前一半最后一个数的索引index

            //如果前一半的最后一个数大于要插入的数
            while (lastIndex >= 0 && arr[lastIndex] > insertValue) {
                //将前一半的最后一个检测数后一一个
                arr[lastIndex + 1] = arr[lastIndex];
                //再将索引前移，再进行比较
                lastIndex--;
            }
            //如果上来要插入的数大于前一半最后一个数，那么就直接无任何变化；反之就要插入到空出来的那个位置了。
            if(lastIndex + 1 != i)
            {
                arr[lastIndex + 1] = insertValue;
            }

            System.out.printf("The %d time sorting array is : ", i);
            System.out.println(Arrays.toString(arr));
        }
    }
}
