package Chapter05.Tree.HuffmanCode;

import java.io.*;
import java.util.*;

/**
 * Huffman 编码就是把字符作为 Huffman Tree 的叶子节点，左边记 0，右边记 1，
 * 从根到叶子的路径就是该字符的编码；出现频率越高，离根越近，编码越短。
 * */
public class HuffmanCodeDemo {
    //将Huffman Code 存放在Map<Byte, String>
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //生成code时，需要拼接路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//        System.out.println(huffmanCodeBytes.length);
//
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        String result = new String(sourceBytes);
//        System.out.println(result);

        String srcFile = "Chapter05/Tree/HuffmanCode/test.jpg";
        String zipFile = "Chapter05/Tree/HuffmanCode/test.zip";
        String destFile = "Chapter05/Tree/HuffmanCode/test_unzip.jpg";
        zipFile(srcFile, zipFile);
        unzipFile(zipFile, destFile);

    }

    public static void unzipFile(String zipFile, String destFile) {
        try(FileInputStream fis = new FileInputStream(zipFile);
            ObjectInputStream ois = new ObjectInputStream(fis))
        {
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            try(FileOutputStream fos = new FileOutputStream(destFile)) {
                fos.write(bytes);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //将一个文件进行压缩
    public static void zipFile(String srcFile, String destFile){
        try(FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            //压缩源文件
            byte[] huffmanCodeBytes = huffmanZip(buffer);
            //把哈夫曼编码后的字节数组写入到压缩文件中去
            oos.writeObject(huffmanCodeBytes);
            //以对象流的的方式写入哈夫曼编码，为了我们之后恢复文件时使用
            oos.writeObject(huffmanCodes);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanCodeBytes 哈夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        // 1. 先得到 huffmanCodeBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < huffmanCodeBytes.length; i++){
            //判断是不是最后一个字节
            boolean flag = (i == huffmanCodeBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanCodeBytes[i]));
        }
        // 2. 反转哈夫曼编码表
        Map<String, Byte> reverseHuffmanCodes = new HashMap<String, Byte>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
        }

        // 3. 根据反转后的编码表解码
        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < stringBuilder.length(); ){
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while(flag) {
                String key = stringBuilder.substring(i, i + count);
                b = reverseHuffmanCodes.get(key);

                if(b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }

            list.add(b);
            i += count;
        }

        // 4. 把 List<Byte> 转成 byte[]
        byte[] result = new byte[list.size()];

        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;

    }

    private static String byteToBitString(boolean needPad, byte b) {
        //使用一个变量保存b，将b转成int类型
        int temp = b;
        if (needPad) {
            temp |= 256;
        }

        String str =  Integer.toBinaryString(temp);

        if(needPad) {
            return str.substring(str.length() - 8);
        }
        else {
            return str;
        }
    }

    private static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
        else{
            System.out.println("The binary tree is empty");
        }

    }

    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<Node>();

        //遍历bytes,统计每个byte出现的次数 --> map[key, value]
        Map<Byte, Integer> counts = new HashMap<>();
        for(Byte byteVal : bytes){
            Integer count = counts.get(byteVal);
            if(count == null){
                counts.put(byteVal, 1);
            }
            else{
                counts.put(byteVal, count + 1);
            }
        }

        //把每个键值对转成Node对象
        for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //通过一个List创建对应的Huffman tree
    private static Node createBinaryTree(List<Node> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static Map<Byte, String> getCodes(Node root) {
        if(root == null){
            return null;
        }
//        getCodes(huffmanTreeRoot, "", stringBuilder);
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成Huffman tree对应的Huffman code
    /**
     * 将传入的node节点的所有叶子节点的Huffman code得到，并放入到HuffmanCodes集合中
     * @param node 传入节点
     * @param code 左子节点是“0”； 右子节点是“1”
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);

        if(node != null){
            //判断当前节点是叶子节点还是非叶子节点
            if(node.data == null){
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            }
            else {
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //编写一个方法，将字符串对应的byte[]数组，再通过huffmanCodes，返回一个压缩后的byte[]
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }
        else{
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的数组
        byte[] result = new byte[len];
        int index = 0;
        for(int i = 0; i < stringBuilder.length(); i = i + 8){ //每八位对应一个Byte
            String strByte;
            if(i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }
            else{
                strByte = stringBuilder.substring(i, i + 8);
            }

            result[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return result;
    }

    //编写一个方法，将所有方法封装起来
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createBinaryTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的Huffman code, 压缩得到压缩后的Huffman编码字节数组
        return zip(bytes, huffmanCodes);
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node[data=" + data + ", weight=" + weight + "]";
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
