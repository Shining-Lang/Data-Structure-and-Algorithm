package Chapter05.Tree.ThreadedBinaryTree;

import com.sun.source.tree.BinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "peter");
        HeroNode node4 = new HeroNode(8, "joe");
        HeroNode node5 = new HeroNode(10, "smith");
        HeroNode node6 = new HeroNode(14, "flora");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.infixThreadedNodes();

        HeroNode left = node5.getLeft();
        System.out.println(left);

        System.out.println("using threaded way to show threaded binary tree: ");
        threadedBinaryTree.infixThreadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void infixThreadedNodes()
    {
        pre = null;
        infixThreadedNodes(root);
    }

    //遍历中序线索化binary tree的方法
    /**
     * 它是从中序遍历“先左、再自己、再右”推出来的：
     * 先一路找最左；访问后能走线索就走线索；不能走线索时，说明遇到了真实右子树，于是进入右子树继续找最左。
     * */
    public void infixThreadedList() {
        HeroNode node = root;
        while (node != null) {
            // 找到当前子树中序遍历的第一个节点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);

            // 如果当前节点的右指针是后继线索，就一直沿着线索访问
            while(node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            // 如果 rightType == 0，说明右指针指向右子树
            node = node.getRight();
        }
    }

    //编写对binary tree进行中序线索化的方法
    public void infixThreadedNodes(HeroNode currentNode){
        if(currentNode == null){
            return;
        }

        //先线索化左子树
        if(currentNode.getLeftType() == 0) {
            infixThreadedNodes(currentNode.getLeft());
        }
        //线索化当前节点
        if(currentNode.getLeft() == null){
            currentNode.setLeft(pre);
            currentNode.setLeftType(1);
        }

        if(pre != null && pre.getRight() == null) {
            pre.setRight(currentNode);
            pre.setRightType(1);
        }
        //每处理完一个节点后，让当前节点成为下一个节点的前驱节点
        pre = currentNode;

        //线索化右子树
        if(currentNode.getRightType() == 0) {
            infixThreadedNodes(currentNode.getRight());
        }
    }

    public void preThreadedNodes()
    {
        pre = null;
        preThreadedNodes(root);
    }

    //遍历前序线索化树
    /**
     * 前序线索化遍历的逻辑是：当前节点先打印；打印后，如果有真实左子树就进入左子树，否则沿着 right 找前序中的下一个节点。
     * */
    public void preThreadedList() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            if(node.getLeftType() == 0) {
                node = node.getLeft();
            }
            else {
                node = node.getRight();
            }
        }
    }

    //编写对binary tree进行前序线索化的方法
    public void preThreadedNodes(HeroNode currentNode){
        if(currentNode == null){
            return;
        }

        if(currentNode.getLeft() == null){
            currentNode.setLeft(pre);
            currentNode.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null) {
            pre.setRight(currentNode);
            pre.setRightType(1);
        }
        pre = currentNode;

        if(currentNode.getLeftType() == 0) {
            preThreadedNodes(currentNode.getLeft());
        }

        if(currentNode.getRightType() == 0) {
            preThreadedNodes(currentNode.getRight());
        }
    }

    public void postThreadedNodes()
    {
        pre = null;
        postThreadedNodes(root);
    }

    /**
     * 1. 从 root 开始，找到后序遍历的第一个节点
     * 2. 第一个节点一定是：
     *    优先一路往左走；
     *    如果没有左子树，就往右走；
     *    直到走到叶子节点
     *
     * 3. 访问这个节点
     *
     * 4. 找它的后继：
     *    如果 rightType == 1，说明 right 是后序后继线索，直接走
     *    如果没有后继线索，就需要知道它的父节点，判断下一步该去哪
     * */
    public void postThreadedList() {
        HeroNode currentNode = root;

        while(currentNode != null && currentNode.getLeftType() == 0) {
            currentNode = currentNode.getLeft();
        }

        while(currentNode != null) {
            System.out.println(currentNode);

            if(currentNode.getRightType() == 1) {
                currentNode = currentNode.getRight();
            } else {
                HeroNode parent = currentNode.getParent();

                if(parent == null) {
                    currentNode = null;
                } else if(parent.getRight() == currentNode || parent.getRightType() == 1 || parent.getRight() == null) {
                    currentNode = parent;
                } else {
                    currentNode = parent.getRight();

                    while(true) {
                        if(currentNode.getLeftType() == 0) {
                            currentNode = currentNode.getLeft();
                        } else if(currentNode.getRightType() == 0) {
                            currentNode = currentNode.getRight();
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public void postThreadedList(HeroNode node) {
        if(node == null) {
            return;
        }

        if(node.getLeftType() == 0) {
            postThreadedList(node.getLeft());
        }

        if(node.getRightType() == 0) {
            postThreadedList(node.getRight());
        }

        System.out.println(node);
    }

    //编写对binary tree进行后序线索化的方法
    public void postThreadedNodes(HeroNode currentNode) {
        if(currentNode == null){
            return;
        }

        if(currentNode.getLeftType() == 0) {
            postThreadedNodes(currentNode.getLeft());
        }

        if(currentNode.getRightType() == 0) {
            postThreadedNodes(currentNode.getRight());
        }

        if(currentNode.getLeft() == null){
            currentNode.setLeft(pre);
            currentNode.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null) {
            pre.setRight(currentNode);
            pre.setRightType(1);
        }
        pre = currentNode;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("The binary tree is empty");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("The binary tree is empty");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("The binary tree is empty");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int id) {
        if (this.root != null) {
            return this.root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int id) {
        if (this.root != null) {
            return this.root.infixOrderSearch(id);
        } else {
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

    private int leftType; //如果为0,则表示指向左子树；如果为1，则表示指向前驱节点
    private int rightType; //如果为0,则表示指向右子树；如果为1,则表示指向后继节点

    private HeroNode parent;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }
}
