package Chapter05.Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
        else {
            System.out.println("The binary tree is empty");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //遍历arr数组，将每个元素构建成一个Node，然后放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for(int value : arr){
            nodes.add(new Node(value));
        }

        while(nodes.size()>1){
            //先排序
            Collections.sort(nodes);
            System.out.println(nodes.toString());

            //取出根节点权值最小的两棵二叉树
            //权值最小
            Node leftNode = nodes.get(0);
            //权值第二小
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        System.out.println(nodes.toString());
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Value: " + value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大
        return this.value - o.value;
    }
}
