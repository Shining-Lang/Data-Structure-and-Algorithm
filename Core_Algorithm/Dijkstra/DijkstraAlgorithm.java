package Core_Algorithm.Dijkstra;

public class DijkstraAlgorithm {
    private static final int INF = 10000; // 用10000表示正无穷（不连通）

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E'};
        // 有向/无向图的邻接矩阵
        int[][] weight = {
                /*A*/ {0, 4, 2, INF, INF},
                /*B*/ {4, 0, 1, 2, INF},
                /*C*/ {2, 1, 0, 5, 6},
                /*D*/ {INF, 2, 5, 0, 1},
                /*E*/ {INF, INF, 6, 1, 0}
        };

        dijkstra(vertexes, weight, 0); // 从 0 号顶点（A）出发
    }

    /**
     * Dijkstra 算法实现
     * @param vertexes 顶点数组
     * @param weight 邻接矩阵
     * @param vs 起点下标
     */
    public static void dijkstra(char[] vertexes, int[][] weight, int vs) {
        int n = vertexes.length;

        // 1. dis数组：记录起点到各个顶点的当前最短距离
        int[] dis = new int[n];

        // 2. visited数组：标记顶点是否已确定最短路径（1为确定，0为未确定）
        int[] visited = new int[n];

        // 初始化数据
        for (int i = 0; i < n; i++) {
            dis[i] = weight[vs][i]; // 起点直接能到的点记录权重，连不通的就是 INF
        }
        visited[vs] = 1; // 起点自身标记为已确定

        // 核心贪心大循环：除了起点，还需要确定剩下的 n-1 个顶点
        for (int i = 1; i < n; i++) {

            // 步骤一：贪心选择。在所有未确定的顶点中，挑一个距离起点最近的
            int min = INF;
            int k = -1; // 用来记录选中的顶点下标
            for (int j = 0; j < n; j++) {
                if (visited[j] == 0 && dis[j] < min) {
                    min = dis[j];
                    k = j;
                }
            }

            // 如果找不到更近的点（剩下的点都连不通了），提前结束
            if (k == -1) break;

            // 步骤二：纳入版图
            visited[k] = 1;

            // 步骤三：松弛操作（Relaxation）。以 k 为跳板，刷新它的邻居们
            for (int j = 0; j < n; j++) {
                // 条件：j没有被确定 + k到j有通路 + (起点到k的距离 + k到j的距离 < 起点直接到j的旧距离)
                if (visited[j] == 0 && weight[k][j] != INF && (dis[k] + weight[k][j] < dis[j])) {
                    dis[j] = dis[k] + weight[k][j]; // 抄近路成功，刷新账本！
                }
            }
        }

        // 打印最终账本
        System.out.println("从顶点 " + vertexes[vs] + " 出发的最短距离结果：");
        for (int i = 0; i < n; i++) {
            System.out.println(vertexes[vs] + " -> " + vertexes[i] + " : " + (dis[i] == INF ? "∞" : dis[i]));
        }
    }
}
