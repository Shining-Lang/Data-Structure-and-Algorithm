package Chapter04.Search;

import java.util.ArrayList;

//二分查找的前提是，数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 34, 55, 612, 2516, 9999};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 9999);
        System.out.println(resIndex);

        System.out.println();
        int[] arr2 = {1, 8, 89, 1000, 1000, 1000, 1000, 9999};
        ArrayList<Integer> list2 = binarySearch2(arr2, 0, arr2.length - 1, 1000);
        if(list2.isEmpty()){
            System.out.println("No value was found");
        }
        else
        {
            System.out.println(list2);
        }

    }

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param targetValue 要查找的值
     * @return 如果找到返回下标，没找到返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int targetValue){
        //当left > right时，说明递归整个数组但是没有找到。
        if(left > right){
            return -1;
        }

        int mid = (left + right)/2;
        int midValue = arr[mid];

        if(targetValue > midValue){
            return binarySearch(arr, mid + 1, right, targetValue);
        }
        else if(targetValue < midValue){
            return binarySearch(arr, left, mid - 1, targetValue);
        }
        else{
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int targetValue){
        //当left > right时，说明递归整个数组但是没有找到。
        if(left > right){
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if(targetValue > midValue){
            return binarySearch2(arr, mid + 1, right, targetValue);
        }
        else if(targetValue < midValue){
            return binarySearch2(arr, left, mid - 1, targetValue);
        }
        else{
            ArrayList<Integer> targetIndexList = new ArrayList<Integer>();
            int tempIndex = mid  - 1;
            while (tempIndex >= 0 && arr[tempIndex] == targetValue) {
                targetIndexList.add(tempIndex);
                tempIndex--;
            }

            targetIndexList.add(mid);

            tempIndex = mid + 1;
            while (tempIndex < arr.length && arr[tempIndex] == targetValue) {
                targetIndexList.add(tempIndex);
                tempIndex++;
            }

            return targetIndexList;
        }
    }
}
