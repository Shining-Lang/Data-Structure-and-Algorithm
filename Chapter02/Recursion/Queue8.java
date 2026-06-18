package Chapter02.Recursion;

public class Queue8 {
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("The number of solution is " + count);
    }

    //放置第n个皇后,注意第一个皇后是0.
    private void check(int n){
        if(n == max){
            print();
            System.out.println("-------------------------------------");
            return;
        }
        for(int i = 0; i < max; i++){
            arr[n] = i;
            if(judge(n)){
                check(n + 1);
            }
        }
    }

    //查看当我们放置第n个皇后时，检测该皇后是否和前面已经摆放的n-1个皇后冲突
    private boolean judge(int n){
        for(int i = 0; i < n; i++){
            //arr[i] == arr[n]表示在同一列
            //Math.abs(n - i) == Math.abs(arr[n] - arr[i])表示是否在同一斜线
            if(arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    //打印皇后摆放的位置
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
