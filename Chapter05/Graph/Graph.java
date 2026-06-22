package Chapter05.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;

    //定义一个boolean数组，记录某个节点是都被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int numOfVertices = 5;
        String[] vertices = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(numOfVertices);
        for(String vertex : vertices){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    public Graph(int numberOfVertices){
        edges = new int[numberOfVertices][numberOfVertices];
        vertexList = new ArrayList<>(numberOfVertices);
        numOfEdges = 0;
    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for(int i = 0; i < vertexList.size(); i++){
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for(int j = v2 + 1; j < vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisited, int i){
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        //查找节点i得第一个邻接节点
        int w = getFirstNeighbor(i);
        while(w != -1){
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过，我们就应该去访问邻接点的下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    private void bfs(boolean[] isVisited, int i) {
        int u; // 队列头节点对应的下标
        int w; // 邻接节点下标
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问当前节点
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        // 当前节点入队
        queue.addLast(i);
        while(!queue.isEmpty()) {
            // 取出队列头节点
            u = queue.removeFirst();
            // 得到第一个邻接节点
            w = getFirstNeighbor(u);

            while(w != -1) {
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    // 已访问的邻接节点入队
                    queue.addLast(w);
                }
                // 继续找 u 的下一个邻接节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for(int i = 0; i < getNumOfVertex(); i++){
            if(!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    //对dfs函数进行重载
    public void dfs(){
        isVisited = new boolean[getNumOfVertex()];
        for(int i = 0; i < getNumOfVertex(); i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    public void showGraph(){
        for(int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    //返回节点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回节点i(下标)对应的数据
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    //返回v1和v2得权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertexName){
        vertexList.add(vertexName);
    }
    //添加边，当前是无向图
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}


