package Core_Algorithm.Floyd;

public class FloydAlgorithm {
    private static final int INF = 10000; // 表示正无穷（不连通）

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D'};
        // 邻接矩阵（可以有负权边，但不能有负权回路）
        int[][] matrix = {
                /*A*/ {0, 5, INF, 7},
                /*B*/ {INF, 0, 4, 2},
                /*C*/ {3, INF, 0, INF},
                /*D*/ {INF, INF, 1, 0}
        };

        floyd(vertexes, matrix);
    }

    /**
     * Floyd 算法实现
     */
    public static void floyd(char[] vertexes, int[][] matrix) {
        int n = vertexes.length;
        // dis[][] 矩阵用于存放任意两点之间的最短距离，初始化为原图的邻接矩阵
        int[][] dis = matrix;

        // 【最核心代码】三层 for 循环
        // 第一层：k 代表当前开放的中转站（跳板）
        for (int k = 0; k < n; k++) {
            // 第二层：i 代表出发点
            for (int i = 0; i < n; i++) {
                // 第三层：j 代表目的地
                for (int j = 0; j < n; j++) {
                    // 如果从 i 到 k，或者从 k 到 j 连不通，就无法中转
                    if (dis[i][k] != INF && dis[k][j] != INF) {
                        // 核心状态转移方程：看看直达近，还是绕道 k 进？
                        if (dis[i][k] + dis[k][j] < dis[i][j]) {
                            dis[i][j] = dis[i][k] + dis[k][j]; // 抄近路成功，刷新总表！
                        }
                    }
                }
            }
        }

        // 打印最终的“全图航线最短路径表”
        System.out.println("--- 任意两点之间的最短距离矩阵 ---");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((dis[i][j] == INF ? "∞" : dis[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
