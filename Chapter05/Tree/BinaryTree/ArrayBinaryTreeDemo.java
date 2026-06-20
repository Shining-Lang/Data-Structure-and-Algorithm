package Chapter05.Tree.BinaryTree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        System.out.println("前序遍历：");
        arrayBinaryTree.preOrder();
        System.out.println();

        System.out.println("中序遍历：");
        arrayBinaryTree.infixOrder();
        System.out.println();

        System.out.println("后序遍历：");
        arrayBinaryTree.postOrder();
        System.out.println();
    }
}
/**
 * 顺序存储binary tree 一般只考虑完全二叉树， 完全二叉树共有2^k - 1个元素， k表示二叉树的层数
 * index为n的元素，它的left child node's index is 2*n+1, right child node's index is 2*n+2,
 * parent node's index is (n-1)/2
 * */
class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    //重载一个方法
    public void preOrder()
    {
        preOrder(0);
    }

    public void infixOrder()
    {
        infixOrder(0);
    }

    public void postOrder()
    {
        postOrder(0);
    }

    //编写一个方法，完成顺序存储binaryTree的前序遍历
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("The array is empty.");
            return;
        }
        System.out.print(arr[index] + " ");
        //向左递归遍历
        if(index * 2 + 1 < arr.length){
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if(index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }
    }

    //编写一个方法，完成顺序存储binaryTree的中序遍历
    public void infixOrder(int index) {
        if(arr == null || arr.length == 0){
            System.out.println("The array is empty.");
            return;
        }
        if(index * 2 + 1 < arr.length){
            infixOrder(index * 2 + 1);
        }

        System.out.print(arr[index] + " ");

        if(index * 2 + 2 < arr.length){
            infixOrder(index * 2 + 2);
        }
    }

    //编写一个方法，完成顺序存储binaryTree的后序遍历
    public void postOrder(int index) {
        if(arr == null || arr.length == 0){
            System.out.println("The array is empty.");
            return;
        }

        if(index * 2 + 1 < arr.length){
            postOrder(index * 2 + 1);
        }

        if(index * 2 + 2 < arr.length){
            postOrder(index * 2 + 2);
        }

        System.out.print(arr[index] + " ");
    }

}
