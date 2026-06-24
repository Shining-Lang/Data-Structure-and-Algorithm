package Core_Algorithm.Prim;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {

    }

}

//创建最小生成树
class MinTree {
    public void createGraph(MGraph graph, int vertexes, char[] data, int[][] weight) {
        int i, j;
        for(i = 0; i < vertexes; i++) {
            graph.data[i] = data[i];
            for(j = 0; j < vertexes; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for(int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法
    public void prim(MGraph graph, int vertex) {
        // visited 数组用于标记顶点是否已被加入最小生成树（1表示已访问，0表示未访问）
        int[] visited = new int[graph.vertexes];

        // 把传入的起始顶点标记为已访问
        visited[vertex] = 1;

        // h1 和 h2 用来记录每一轮找到的权值最小的边的两个端点下标
        int h1 = -1, h2 = -1;
        int minWeight; // 移出初始赋值，在循环内部重置

        // N个顶点，需要找 N-1 条边。所以外层循环执行 graph.vertexes - 1 次
        for(int i = 1; i < graph.vertexes; i++) {

            // 【核心修正】每一轮寻找新边前，必须重置最小权重为无穷大
            minWeight = 10000;

            // 双重遍历整个邻接矩阵，寻找连接“已访问集合”与“未访问集合”的最短边
            for(int j = 0; j < graph.vertexes; j++) {     // j 表示已访问过的节点
                for(int k = 0; k < graph.vertexes; k++) { // k 表示还没有被访问过的节点

                    // 贪心策略判断：一条边的起点已访问、终点未访问，且权重比当前记录的还要小
                    if(visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                        minWeight = graph.weight[j][k]; // 更新本轮的最小权重
                        h1 = j;                         // 记录起点
                        h2 = k;                         // 记录终点
                    }
                }
            }

            // 找到本轮最便宜的边，打印输出
            System.out.println("Edge <" + graph.data[h1] + ", " + graph.data[h2] + "> : " + minWeight);

            // 将新找到的终点 k (即 h2) 标记为已访问，拉入我们的“通电网络”
            visited[h2] = 1;
        }
    }
}

class MGraph {
    int vertexes; //存放图的节点个数
    char[] data; //存放节点的数据
    int[][] weight; //存放边，就是我们得邻接矩阵

    public MGraph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }
}
