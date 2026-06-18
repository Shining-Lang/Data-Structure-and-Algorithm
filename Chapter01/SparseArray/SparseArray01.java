package Chapter01.SparseArray;

public class SparseArray01 {
    public static void main(String[] args) {
    /*
    * 二维数组转稀疏数组的思路
    1.遍历原始的二维数组，得到有效数据的个数sum
    2.根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
    3.将二维数组的有效数据数据存入到稀疏数组

    * 稀疏数组转原始的二维数组的思路
    1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2=int[11[11]
    2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可.
    * */

        //假设现在的棋盘为11X11，第2行第3列的值为1，第3行第4列的值为2
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        for(int[] row : chessArr1){
            for(int value : row){
                System.out.print(value + " ");
            }
            System.out.println();
        }

        //二维数组变稀疏数组
        int sum = 0;
        for(int i = 0; i < chessArr1.length; i++){
            for(int j = 0; j < chessArr1[i].length; j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        System.out.println();

        int[][] chessArr2 = new int[sum + 1][3];
        chessArr2[0][0] = chessArr1.length;
        chessArr2[0][1] = chessArr1[0].length;
        chessArr2[0][2] = sum;

        int count = 0;
        for(int i = 0; i < chessArr1.length; i++){
            for(int j = 0; j < chessArr1[i].length; j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    chessArr2[count][0] = i;
                    chessArr2[count][1] = j;
                    chessArr2[count][2] = chessArr1[i][j];
                }
            }
        }
        for(int[] row : chessArr2){
            for(int value : row){
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        System.out.println();
        int[][] chessArr3 = new int[chessArr2[0][0]][chessArr2[0][1]];
        for(int i = 1; i <= sum; i++){
            chessArr3[chessArr2[i][0]][chessArr2[i][1]] = chessArr2[i][2];
        }
        for(int i = 0; i < chessArr3.length; i++){
            for(int j = 0; j < chessArr3[i].length; j++){
                System.out.print(chessArr3[i][j] + " ");
            }
            System.out.println();
        }

    }


/*
* 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

* 稀疏数组的处理方法是:
  1)记录数组一共有几行几列，有多少个不同的值
  2)把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模I
* */

}
