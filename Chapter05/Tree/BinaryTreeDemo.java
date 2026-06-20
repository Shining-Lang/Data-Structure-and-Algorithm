package Chapter05.Tree;

import org.w3c.dom.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node1 = new HeroNode(2, "jack");
        HeroNode node2 = new HeroNode(3, "peter");
        HeroNode node3 = new HeroNode(4, "joe");
        HeroNode node4 = new HeroNode(5, "smith");

        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);


        binaryTree.setRoot(root);

        System.out.println("前序遍历：");
        binaryTree.preOrder(); //1, 2, 3, 5, 4
        System.out.println();

        System.out.println("中序遍历：");
        binaryTree.infixOrder(); //2, 1, 5, 3, 4
        System.out.println();

        System.out.println("后序遍历：");
        binaryTree.postOrder(); //2, 5, 4, 3, 1
        System.out.println();

        //前序遍历方式
        System.out.println("前序查找：");
        HeroNode resNode = binaryTree.preOrderSearch(15);
        System.out.println(resNode);
        System.out.println();

        System.out.println("中序查找：");
        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
        System.out.println(resNode1);
        System.out.println();

        System.out.println("后序查找：");
        HeroNode resNode2 = binaryTree.postOrderSearch(1);
        System.out.println(resNode2);
        System.out.println();

        System.out.println("删除节点：");
        binaryTree.infixOrder();
        System.out.println("---------------------");
        binaryTree.deleteNode(3);
        binaryTree.infixOrder();
        System.out.println();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }
        else {
            System.out.println("The binary tree is empty");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }
        else {
            System.out.println("The binary tree is empty");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }
        else {
            System.out.println("The binary tree is empty");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int id){
        if(this.root != null){
            return this.root.preOrderSearch(id);
        }
        else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int id){
        if(this.root != null){
            return this.root.infixOrderSearch(id);
        }
        else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int id){
        if(this.root != null){
            return this.root.postOrderSearch(id);
        }
        else {
            return null;
        }
    }

    //删除节点
    public void deleteNode(int id){
        if(this.root != null){
            if(this.root.getId() == id){
                root = null;
            }
            else {
                this.root.deleteNode(id);
            }
        }
        else {
            System.out.println("The binary tree is empty");
        }
    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public String toString(){
        return "HeroNode [id=" + id + ", name=" + name + "]";
    }

    //编写前序遍历方法
    public void preOrder(){
        System.out.println(this); // 先输出父节点
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历方法
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //编写后序遍历方法
    public void postOrder(){
        //递归向前子树后序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //编写前序查找
    public HeroNode preOrderSearch(int id){
        if(this.id == id){
            return this;
        }

        HeroNode resultNode = null;

        if(this.left != null){
            resultNode = this.left.preOrderSearch(id);
        }
        if(resultNode != null){
            return resultNode;
        }

        if(this.right != null){
            resultNode = this.right.preOrderSearch(id);
        }
        return resultNode;
    }

    //编写中序查找
    public HeroNode infixOrderSearch(int id){
        HeroNode resultNode = null;

        if(this.left != null){
            resultNode = this.left.infixOrderSearch(id);
        }
        if(resultNode != null){
            return resultNode;
        }

        if(this.id == id){
            return this;
        }

        if(this.right != null){
            resultNode = this.right.infixOrderSearch(id);
        }
        return resultNode;
    }

    //编写后序查找
    public HeroNode postOrderSearch(int id){
        HeroNode resultNode = null;

        if(this.left != null){
            resultNode = this.left.postOrderSearch(id);
        }
        if(resultNode != null){
            return resultNode;
        }

        if(this.right != null){
            resultNode = this.right.postOrderSearch(id);
        }
        if(resultNode != null){
            return resultNode;
        }

        if(this.id == id){
            return this;
        }
        return resultNode;
    }

    //递归删除节点
    //规定：1.如果删除的节点为叶子节点，则删除该节点； 2.如果删除的节点为非叶子节点，则删除该子树
    public void deleteNode(int id){
        //如果当前节点的左子节点不为空，并且左子节点就是要删除的节点
        if(this.left != null && this.left.getId() == id){
            this.left = getReplacementNode(this.left);
            return;
        }
        //如果当前节点的右子节点不为空，并且右子节点就是要删除的节点
        if(this.right != null && this.right.getId() == id){
            this.right = getReplacementNode(this.right);
            return;
        }
        //向左子树进行递归删除
        if(this.left != null){
            this.left.deleteNode(id);
        }
        //向右子树进行递归删除
        if(this.right != null){
            this.right.deleteNode(id);
        }
    }

    private HeroNode getReplacementNode(HeroNode deletedNode){
        // 情况1：叶子节点
        if(deletedNode.left == null && deletedNode.right == null){
            return null;
        }

        // 情况2：只有右子节点
        if(deletedNode.left == null){
            return deletedNode.right;
        }

        // 情况3：只有左子节点
        if(deletedNode.right == null){
            return deletedNode.left;
        }

        // 情况4：左右子节点都有，用左子节点替代 deletedNode
        HeroNode replacementNode = deletedNode.left;

        // 把原来的右子树接到 replacementNode 这棵树的最右边
        HeroNode temp = replacementNode;
        while(temp.right != null){
            temp = temp.right;
        }
        temp.right = deletedNode.right;

        return replacementNode;
    }
}
