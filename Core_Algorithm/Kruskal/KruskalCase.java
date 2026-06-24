package Core_Algorithm.Kruskal;

import java.util.Arrays;
import java.util.Comparator;

public class KruskalCase {

    private int edgeNum;           // 边的个数
    private char[] vertexes;       // 顶点数组
    private int[][] matrix;        // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE; // 使用系统最大值表示不连通

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E'};
        // 这是一个无向图的邻接矩阵，0表示自己到自己，INF表示不连通
        int[][] matrix = {
                /*A*/ {0, 4, 2, 2, INF},
                /*B*/ {4, 0, INF, INF, 5},
                /*C*/ {2, INF, 0, 1, INF},
                /*D*/ {2, INF, 1, 0, 3},
                /*E*/ {INF, 5, INF, 3, 0}
        };

        KruskalCase kruskal = new KruskalCase(vertexes, matrix);
        kruskal.kruskal();
    }

    // 构造函数
    public KruskalCase(char[] vertexes, int[][] matrix) {
        int vlen = vertexes.length;
        this.vertexes = new char[vlen];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vlen);
        this.matrix = matrix;

        // 统计边的棵数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * Kruskal 核心算法
     */
    public void kruskal() {
        int index = 0; // 记录最终结果数组的索引

        // 1. 用于保存“并查集”的终点数组。下标 i 代表顶点，值代表该顶点在帮派中的“老大”
        int[] ends = new int[vertexes.length];

        // 2. 创建结果数组，用于存放最终生成的最小生成树的边
        EdgeData[] rets = new EdgeData[vertexes.length - 1];

        // 3. 获取图中所有的边，并按照权重从小到大排序
        EdgeData[] edges = getEdges();
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight)); // 贪心基石：按价格排序

        // 4. 遍历边集数组，开始贪心选购
        for (int i = 0; i < edgeNum; i++) {
            // 获取第 i 条边的两个端点下标
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            // 【核心判环】寻找这两个端点在并查集中的“最高帮派老大”
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            // 如果老大不是同一个人，说明连上这条边不会形成环
            if (m != n) {
                ends[m] = n;       // 结拜！让 m 的老大的老大变成 n（合并集合）
                rets[index++] = edges[i]; // 成功买下这条边
            }
        }

        // 5. 打印最终选购的最小生成树
        System.out.println("Kruskal 最小生成树为：");
        int totalWeight = 0;
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
            totalWeight += rets[i].weight;
        }
        System.out.println("最小生成树总权重为: " + totalWeight);
    }

    /**
     * 并查集核心：获取下标为 i 的顶点的最高帮派老大
     * @param ends ：记录各个顶点真正终点的数组
     * @param i    ：传入的顶点下标
     * @return 返回该顶点对应的最高老大的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    // 根据顶点字符获取对应的矩阵下标（如 'A' -> 0）
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) return i;
        }
        return -1;
    }

    // 从邻接矩阵中提取出所有的边，转化为对象数组
    private EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EdgeData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }
}

/**
 * 边的类：专门用来对边进行排序和表达
 */
class EdgeData {
    char start; // 边的起点
    char end;   // 边的终点
    int weight; // 边的权重

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge <" + start + ", " + end + "> : " + weight;
    }
}
