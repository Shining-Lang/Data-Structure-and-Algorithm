package Chapter05.Tree.BinarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        else {
            return root.searchParent(value);
        }
    }

    //返回以node为根节点的二叉排序树的最小节点的值
    //删除node为根节点的二叉排序树得最小节点得值
    public int deleteRightMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        deleteNode(target.value);
        return target.value;
    }

    //删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }
        else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }

            Node parent = searchParent(value);

            if(parent == null) {
                // root 是叶子节点
                if(root.left == null && root.right == null) {
                    root = null;
                }
                // root 有左右两棵子树
                else if(root.left != null && root.right != null) {
                    root.value = deleteRightMin(root.right);
                }
                // root 只有一棵子树
                else {
                    root = root.left != null ? root.left : root.right;
                }
                return;
            }

            //如果删除的节点是叶子节点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父节点的左子节点还是右子节点
                if(parent.left != null && parent.left.value == value) {
                    parent.left = null;
                }
                else if(parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            }
            //如果删除的节点有左右两棵子树
            else if(targetNode.left != null && targetNode.right != null){
                targetNode.value = deleteRightMin(targetNode.right);
            }
            else{
                //如果要删除的targetNode有左子节点
                if(targetNode.left != null) {
                    //如果targetNode是parent的左子节点
                    if(parent.left != null && parent.left.value == value) {
                        parent.left = targetNode.left;
                    }
                    else { //如果targetNode是parent的右子节点
                        parent.right = targetNode.left;
                    }
                } else { //如果要删除的targetNode有右子节点
                    if(parent.left != null && parent.left.value == value) {
                        //如果targetNode是parent的左子节点
                        parent.left = targetNode.right;
                    }
                    else { //如果targetNode是parent的右子节点
                        parent.right = targetNode.right;
                    }
                }
            }

        }
    }

    public void add(Node node){
        if(root == null){
            root = node;
        }
        else{
            root.add(node);
        }
    }

    public void infixOrder() {
        if(root != null){
            root.infixOrder();
        }
        else {
            System.out.println("The tree is empty");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    public Node search(int value){
        if(this.value == value){
            return this;
        }
        else if(value < this.value){
            if(this.left != null){
                return this.left.search(value);
            }
            else{
                return null;
            }
        }
        else {
            if(this.right != null){
                return this.right.search(value);
            }
            else{
                return null;
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }
        else{
            if(value < this.value &&  this.left != null){
                return this.left.searchParent(value);
            }
            else if(value >= this.value &&  this.right != null){
                return this.right.searchParent(value);
            }
            else{
                return null;
            }
        }
    }

    //添加节点
    public void add(Node node) {
        if(node == null){
            return;
        }
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }
            else{
                this.left.add(node);
            }
        }
        else {
            if(this.right == null){
                this.right = node;
            }
            else{
                this.right.add(node);
            }
        }
    }

    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" + "value = " + value + '}';
    }
}