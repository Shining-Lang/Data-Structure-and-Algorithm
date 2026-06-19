package Chapter03.Sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args){
        int[] arr = {53, -3, 542, -748, 14, -214};
        radixSortWithNegative(arr);
    }

    public static void radixSortWithNegative(int[] arr) {
        int negativeCount = 0;
        int positiveCount = 0;

        for(int value : arr) {
            if(value < 0) {
                negativeCount++;
            } else {
                positiveCount++;
            }
        }

        int[] negatives = new int[negativeCount];
        int[] positives = new int[positiveCount];

        int negativeIndex = 0;
        int positiveIndex = 0;

        for(int value : arr) {
            if(value < 0) {
                negatives[negativeIndex] = -value;
                negativeIndex++;
            } else {
                positives[positiveIndex] = value;
                positiveIndex++;
            }
        }

        radixSortNonNegative(negatives);
        radixSortNonNegative(positives);

        int index = 0;

        for(int i = negatives.length - 1; i >= 0; i--) {
            arr[index] = -negatives[i];
            index++;
        }

        for(int i = 0; i < positives.length; i++) {
            arr[index] = positives[i];
            index++;
        }

        System.out.println("The final array is: " +  Arrays.toString(arr));
    }

    public static void radixSortNonNegative(int[] arr){
        if(arr == null || arr.length == 0) {
            return;
        }
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际放了多少个data，定义一个一维数组来记录各个桶每次放入data的个数
        int[] bucketElementCounts = new int[10];

        int max = arr[0];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = String.valueOf(max).length();

        for(int i = 0, n = 1; i < maxLength; i++,  n *= 10){
            for(int j = 0; j < arr.length; j++){
                //取出每个元素的位值
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序(一维数组的下标依次取出数据，放入到原来数组中去)
            int index = 0;
            for(int k = 0; k < bucket.length; k++){
                if(bucketElementCounts[k] != 0)
                {
                    for(int l = 0; l < bucketElementCounts[k]; l++){
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }

            System.out.printf("The %d time array is ", i + 1);
            System.out.println(Arrays.toString(arr));
        }
    }
}
