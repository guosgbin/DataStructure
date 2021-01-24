package com.peijun.huffmantree;


import java.util.*;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/24 23:04
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 哈夫曼编码
 */
public class HuffmanCode {

    // nobody knows China better than me
    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();

        String sentence = "nobody knows China better than me";
        PriorityQueue<TreeNode> nodeQueue = huffmanCode.getNodeList(sentence.getBytes());

        huffmanCode.buildHuffmanTree(nodeQueue);

    }

    /**
     * 根结点
     */
    private TreeNode root;

    /**
     * 哈夫曼编码表
     */
    private Map<Byte, String> codeTable;

    /**
     * 存放编码路径
     */
    private StringBuffer sb = new StringBuffer();

    /**
     * 获取每个字符和其权值(出现次数)的结点列表
     *
     * @param bytes
     */
    private PriorityQueue<TreeNode> getNodeList(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        PriorityQueue<TreeNode> queue = new PriorityQueue<>();
        // 保存字符和出现次数的map
        Map<Byte, Integer> map = new HashMap<>();
        for (byte data : bytes) {
            // 假如不存在此key,存一个此key且值为1
            // 假如存在此key,旧值和新值相加即可
            map.merge(data, 1, Integer::sum); // 添加到Map,JDK8的Map新方法
        }
        // 转换为结点
        map.forEach((key, value) -> {
//            byte[] bytes1 = new byte[1];
//            bytes1[0] = key;
//            System.out.println(new String(bytes1) + "=====" + value);
            queue.offer(new TreeNode(value, key));
        });
//        System.out.println(map.size());
        return queue;
    }

    /**
     * 构建哈夫曼树
     *
     * @param nodeQueue
     */
    private void buildHuffmanTree(PriorityQueue<TreeNode> nodeQueue) {
        while (nodeQueue.size() > 1) {
            TreeNode left = nodeQueue.poll();
            TreeNode right = nodeQueue.poll();
            TreeNode father = new TreeNode(left.weight + right.weight, null);
            father.left = left;
            father.right = right;
            nodeQueue.offer(father);
        }
        root = nodeQueue.poll();
    }

    /**
     * 生成哈夫曼编码列表
     *
     * @param node
     * @param code
     * @param sb
     */
    private void getCodes(TreeNode node, String code, StringBuffer sb) {
        // TODO
    }

    // 将字符串生成哈夫曼编码的列表


    /**
     * 二叉树的结点对象
     */
    class TreeNode implements Comparable<TreeNode> {
        /**
         * 权值
         */
        private int weight;
        /**
         * 左子树指针
         */
        private TreeNode left;
        /**
         * 右子树指针
         */
        private TreeNode right;
        /**
         * 该结点存储的字节
         */
        private Byte data;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public Byte getData() {
            return data;
        }

        public void setData(Byte data) {
            this.data = data;
        }

        public TreeNode(int weight, Byte data) {
            this.weight = weight;
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        /**
         * 按照权值升序
         */
        @Override
        public int compareTo(TreeNode node) {
            return this.weight - node.weight;
        }
    }
}
