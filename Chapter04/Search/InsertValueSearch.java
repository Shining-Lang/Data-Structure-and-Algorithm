package Chapter04.Search;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i + 1;
        }
        System.out.println("Initial Array: " + Arrays.toString(arr));
        int result = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("Search result index is: " + result);

        int[] arr2 = {5, 5, 6, 7, 7, 9, 10, 11, 22};
        ArrayList<Integer> result2 = insertValueSearch2(arr2, 0, arr2.length - 1, 7);
        System.out.println("Search result index is: " + result2);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param targetValue 查找的值
     * @return 如果找到，就返回对应的下标， 如果没有找到就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int targetValue){
        if(left > right || targetValue < arr[0] || targetValue > arr[arr.length - 1]){
            return -1;
        }

        int mid = left + (right - left) * (targetValue - arr[left]) / (arr[right] - arr[left]);

        if(targetValue > arr[mid]){
            return insertValueSearch(arr, mid + 1, right, targetValue);
        }
        else if(targetValue < arr[mid]){
            return insertValueSearch(arr, left, mid - 1, targetValue);
        }
        else{
            return mid;
        }
    }

    //如果右多个目标值存在，那就返回目标值下标的collection
    public static ArrayList<Integer> insertValueSearch2(int[] arr, int left, int right, int targetValue){
        if(left > right || targetValue < arr[0] || targetValue > arr[arr.length - 1]){
            return new ArrayList<Integer>();
        }

        //如果整个有序数组每个值都是一样的，那么我们就可以直接判断是不是要找的目标值；
        // 如果不是要找的目标值， 那么就一定找不到要找的目标值了
        if(arr[left] == arr[right]) {
            if(arr[left] == targetValue) {
                ArrayList<Integer> result = new ArrayList<>();
                for(int i = left; i <= right; i++) {
                    result.add(i);
                }
                return result;
            } else {
                return new ArrayList<Integer>();
            }
        }

        int mid = left + (right - left) * (targetValue - arr[left]) / (arr[right] - arr[left]);

        if(targetValue > arr[mid]){
            return insertValueSearch2(arr, mid + 1, right, targetValue);
        }
        else if(targetValue < arr[mid]){
            return insertValueSearch2(arr, left, mid - 1, targetValue);
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
