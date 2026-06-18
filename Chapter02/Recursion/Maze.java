package Chapter02.Recursion;

public class Maze {
    public static void main(String[] args){
        //先创建一个二维数组代表迷宫
        int[][] map = new int[8][7];
        //使用1来表示墙
        //上下全部置为1
        for(int i = 0; i < 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for(int i = 0; i < 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //地图显示
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
        setWay(map, 1, 1);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    /**
     * @param map 表示地图
     * @param i 表示起始位置的行
     * @param j 表示起始位置的列
     * @return 如果找到通路了，就返回true， 否则返回false
     * */
    public static boolean setWay(int[][] map, int i, int j)
    {
        //约定当map[i][j]为0时，表示还没有走过，当为1时表示墙，当为2时，表示可以走通，当为3时，表示该点已经走过并且走不通
        if(map[6][5] == 2)
        {
            return true;
        }
        else
        {
            if(map[i][j] == 0)
            {
                //假设能走通
                map[i][j] = 2;
                //按照策略按下->右->上->左的顺序走
                if(setWay(map, i+1, j))
                {
                    return true;
                }
                else if (setWay(map, i, j+1))
                {
                    return true;
                }
                else if(setWay(map, i-1, j))
                {
                    return true;
                }
                else if(setWay(map, i, j-1))
                {
                    return true;
                }
                else
                {
                    map[i][j] = 3;
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
