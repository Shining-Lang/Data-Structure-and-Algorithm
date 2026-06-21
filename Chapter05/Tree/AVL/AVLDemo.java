package Chapter05.Tree.AVL;

public class AVLDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

class AVLTree {
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

    public int deleteLeftMax(Node node) {
        Node target = node;
        while (target.right != null) {
            target = target.right;
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
//                targetNode.value = deleteLeftMax(targetNode.left);
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

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) return 0;
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) return 0;
        return right.height();
    }

    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left ==  null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    private void leftRotate() {
        //创建新的节点，以当前节点的值
        Node newNode = new Node(value);
        //把新的节点得左子树设置成当前节点的左子树
        newNode.left = this.left;
        //把新的节点得右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值换成右子节点得值
        this.value = this.right.value;
        //把当前节点的右子树换成右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树换成新的节点
        this.left = newNode;

    }

    //右旋转
    private void rightRotate() {
        //创建一个新的节点，以当前节点的值
        Node newNode = new Node(value);
        //把新的节点的右子树设成当前节点得右子树
        newNode.right = this.right;
        //把新的节点得左子树设置成当前节点的左子树的右子树
        newNode.left = this.left.right;
        //把当前节点得值换成左子节点的值
        this.value = this.left.value;
        //把当前节点的左子树换成左子树得左子树
        this.left = this.left.left;
        //把当前节点的右子树换成新的节点
        this.right = newNode;
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

        //当添加完一个节点后， 如果（右子树的高度 - 左子树的高度）> 1, 则发生左旋转
        if(rightHeight() - leftHeight() > 1){
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }
            else{
                leftRotate();
            }
        }
        //当添加完一个节点后， 如果（左子树的高度 - 右子树的高度）> 1, 则发生右旋转
        if(leftHeight() - rightHeight() > 1){
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }
            else {
                rightRotate();
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
