package com.peijun.test.tree;

import java.util.*;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2021/2/13 16:18
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 哈夫曼编码 测试类
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String testStr = "huang gang jvav";
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] bytes = huffmanCode.zipStr(testStr.getBytes());
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * 根结点
     */
    private TreeNode root;

    /**
     * 哈夫曼编码表
     */
    private Map<Byte, String> codeTable = new HashMap<>();


    // 1. 首先将给定字符串的各个字符对应的权重求出来
    // 2. 将得到的出现比例得到后，构造成一个哈夫曼树
    // 3. 根据得到的哈夫曼树获得各个字符对应额定路径，也就是哈夫曼编码表
    // 4. 根据哈夫曼编码表将原始字符串变为一个二进制编码，按照8位 得到新的byte数组

    /**
     * 压缩字符串
     */
    public byte[] zipStr(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        // 1.初始化字符串的各个字符对应的权重
        PriorityQueue<TreeNode> queue = getNodeList(bytes);
        // 2.构造成一个哈夫曼树
        buildHuffmanTree(queue);
        // 3.根据得到的哈夫曼树获得各个字符对应额定路径，也就是哈夫曼编码表
        Map<Byte, String> codeTable = getCodeTable(root);
        // 4.根据哈夫曼编码表将原始字符串变为一个二进制编码，按照8位 得到新的byte数组
        return zipToByteArray(bytes, codeTable);
    }

    /**
     * 初始化字符串的各个字符对应的权重
     */
    private PriorityQueue<TreeNode> getNodeList(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        // 保存字符和出现次数的map
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            map.merge(aByte, 1, Integer::sum);
        }
        // 转换为结点，放入优先队列中
        PriorityQueue<TreeNode> queue = new PriorityQueue<>();
        map.forEach((key, value) -> {
            queue.offer(new TreeNode(key, value));
        });
        return queue;
    }

    /**
     * 构建哈夫曼树
     */
    private void buildHuffmanTree(PriorityQueue<TreeNode> queue) {
        if (queue == null) {
            return;
        }
        // 优先队列中剩下一个结点，就是哈夫曼树的根结点
        while (queue.size() > 1) {
            // 每次拿最小的两个权重出来构建
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // 构建父结点
            TreeNode father = new TreeNode(null, left.weight + right.weight);
            father.setLeft(left);
            father.setRight(right);
            // 将新的父结点放到优先队列中
            queue.offer(father);
        }
        // 赋值给根结点
        this.root = queue.poll();
    }

    /**
     * 获得哈夫曼编码
     */
    private Map<Byte, String> getCodeTable(TreeNode root) {
        if (root == null) {
            return null;
        }
        getCodeTable(root, "", new StringBuilder());
        return codeTable;
    }

    /**
     * 获得哈夫曼编码
     */
    private void getCodeTable(TreeNode node, String roadValue, StringBuilder sb) {
        if (node == null) {
            return;
        }
        StringBuilder sbb = new StringBuilder(sb);
        sbb.append(roadValue);
        if (node.getLeft() == null && node.getRight() == null) {
            // 当前结点是叶子结点
            codeTable.put(node.getData(), sbb.toString());
        } else {
            // 当前结点不是叶子结点，递归遍历左子树和右子树
            getCodeTable(node.getLeft(), "0", sbb);
            getCodeTable(node.getRight(), "1", sbb);
        }
    }

    /**
     * 根据哈夫曼编码将源byte数组转为压缩后的byte数组
     */
    private byte[] zipToByteArray(byte[] bytes, Map<Byte, String> codeTable) {
        StringBuilder sb = new StringBuilder();
        // 按照哈夫曼编码表将源byte数组转为二进制字节形式的字符串
        for (byte aByte : bytes) {
            sb.append(codeTable.get(aByte));
        }
        // 按照8个一位将二进制形式的字符串转换为新的byte数组
        // 最后一位 表示末尾字节个数，因为最后可能不足8位，存起来是为了解压
        int length = (sb.length() + 7) / 8 + 1; // 得到新byte数组的长度，加1是保存末尾字节个数
        byte[] newBytes = new byte[length];

        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String str;
            if (i + 8 < sb.length()) {
                // 不是最后一个，直接放8位
                str = sb.substring(i, i + 8);
            } else {
                // 最后一个数
                str = sb.substring(i);
            }
            newBytes[index++] = (byte) Integer.parseInt(str, 2);
        }
        newBytes[index] = (byte) (sb.length() % 8); // 记录末尾不足8位的 位数
        return newBytes;
    }

    class TreeNode implements Comparable<TreeNode> {
        private TreeNode left;
        private TreeNode right;
        private Byte data;
        private Integer weight;

        public TreeNode(Byte data, Integer weight) {
            this.data = data;
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

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    ", data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.weight - o.weight;
        }
    }
}
