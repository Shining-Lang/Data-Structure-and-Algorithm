package Core_Algorithm.Knights_Tour_Problem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {

    private static int X = 8; // 棋盘的列数
    private static int Y = 8; // 棋盘的行数
    private static boolean finished = false; // 标记是否成功走完整个棋盘
    private static int[][] chessboard; // 棋盘，记录马走的步数
    private static boolean[] visited;   // 记录棋盘各个位置是否被访问过

    public static void main(String[] args) {
        chessboard = new int[Y][X];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        // 从第 1 行，第 1 列开始跑（下标从 0 开始）
        traversalChessboard(chessboard, 0, 0, 1);
        long end = System.currentTimeMillis();

        System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) + " 毫秒");

        // 输出棋盘结果
        for (int[] row : chessboard) {
            for (int step : row) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 马踏棋盘核心算法（DFS + 回溯）
     * @param chessboard 棋盘
     * @param row 当前马所在的行
     * @param col 当前马所在的列
     * @param step 当前是第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;
        visited[row * X + col] = true; // 转化为一维数组下标标记已访问

        // 1. 获取当前位置下，马儿下一步能跳的所有可能位置
        ArrayList<Point> ps = next(new Point(col, row));

        // 2. 【核心贪心优化】根据下一步位置的“出路多少”进行从小到大排序
        sort(ps);

        // 3. 遍历所有可能跳的位置
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 总是挑未来出路最少的那一个
            // 判断这个点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        // 4. 棋盘没走满，且后面的路全堵死了，触发回溯
        if (step < X * Y && !finished) {
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true; // 成功铺满，通知所有递归层停止回溯
        }
    }

    /**
     * 根据当前位置，计算马还能跳向哪些位置（最多8个）
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();

        // 比如往左上跳“日”：左2上1
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 往右上跳“日”：右2上1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // ... 以此类推，代码中可以凑齐 8 个方向的坐标边界检查 ...
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) ps.add(new Point(p1));
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) ps.add(new Point(p1));
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) ps.add(new Point(p1));
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) ps.add(new Point(p1));
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) ps.add(new Point(p1));
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) ps.add(new Point(p1));

        return ps;
    }

    /**
     * 贪心策略排序：计算各个候选点下一步的出路数，升序排列
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 看看 o1 下一步能走几个位置，o2 下一步能走几个位置
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return Integer.compare(count1, count2); // 升序排列
            }
        });
    }
}
