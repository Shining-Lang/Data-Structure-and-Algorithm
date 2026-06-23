package Core_Algorithm.DynamicProgramming;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4; //背包的最大容量
        int n = val.length; //物品的数量

        //创建二维数组
        //vals[i][j]表示在前i个物品中能够装入容量为j得背包中的最大价值
        int[][] vals = new int[n + 1][m + 1];

        int[][] path = new int[n + 1][m + 1];

        for(int i = 0; i < val.length; i++){
            vals[i][0] = 0;
        }
        for(int j = 0; j < vals[0].length; j++){
            vals[0][j] = 0;
        }

        for(int i = 1; i < vals.length; i++){
            for(int j = 1; j < vals[0].length; j++){
                if(w[i - 1] > j) {
                    vals[i][j] = vals[i - 1][j];
                }
                else {
//                    vals[i][j] = Math.max(vals[i - 1][j], val[i - 1] + vals[i - 1][j - w[i - 1]]);
                    if(vals[i - 1][j] < val[i - 1] + vals[i - 1][j - w[i - 1]]) {
                        vals[i][j] = val[i - 1] + vals[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        vals[i][j] = vals[i - 1][j];
                    }
                }
            }
        }

        for(int i = 0; i < vals.length; i++){
            for(int j = 0; j < vals[i].length; j++){
                System.out.print(vals[i][j] + " ");
            }
            System.out.println();
        }

//        for(int i =0; i < path.length; i++){
//            for(int j =0; j < path[i].length; j++){
//                if(path[i][j] == 1){
//                    System.out.printf("第%d个商品放入背包\n",i);
//                }
//            }
//        }
        int row = path.length - 1;
        int col = path[0].length - 1;
        while(row > 0 &&  col > 0) {
            if(path[row][col] == 1) {
                System.out.printf("第%d个商品放入背包\n",row);
                col = col - w[row - 1];
            }
            row--;
        }
    }
}
