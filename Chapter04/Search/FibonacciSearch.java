package Chapter04.Search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 89);
        System.out.println(index);
    }

    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public static int fibonacciSearch(int[] arr, int targetValue) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0;
        int[] fibonacci = fib();

        while (high > fibonacci[k] - 1) {
            k++;
        }
        //创建一个临时数组，将原arr拷贝到这个临时数组当中，长度为fibonacci[k]， 长度多出来的部分用0填充
        int[] tempArray = Arrays.copyOf(arr, fibonacci[k]);
        //现在我们想让0填充的位置用原arr最后一个数来填充。
        for (int i = high + 1; i < tempArray.length; i++) {
            tempArray[i] = arr[high];
        }
        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            //牢记 fib(k) = fib(k - 1) + fib(k - 2)
            if (targetValue < tempArray[mid]) {
                high = mid - 1;
                k--;
            } else if (targetValue > tempArray[mid]) {
                low = mid + 1;
                k = k - 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}