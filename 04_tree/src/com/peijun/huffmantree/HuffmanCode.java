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
//        HuffmanCode huffmanCode = new HuffmanCode();
//
//        String sentence = "huang gang jvav";
//        PriorityQueue<TreeNode> nodeQueue = huffmanCode.getNodeList(sentence.getBytes());
//
//        huffmanCode.buildHuffmanTree(nodeQueue);
//
////        huffmanCode.getCodes(huffmanCode.root, "",new StringBuffer());
//        Map<Byte, String> codeTable = huffmanCode.getCodeTable(huffmanCode.root);
//
////        codeTable.forEach( (k,v) -> {
////            System.out.println(k + "=====" + v);
////        } );
//
//        byte[] zip = huffmanCode.zip(sentence.getBytes(), codeTable);
//
//
//        System.out.println("====="+ Arrays.toString(zip));
//        System.out.println(zip.length);
//        System.out.println(sentence.getBytes().length);

//        System.out.println(Byte.parseByte("11111111", 2));
        int aByte = Integer.valueOf("11111111", 2);
        byte aByte1 = (byte) aByte;
        System.out.println(aByte1);

    }

    /**
     * 根结点
     */
    private TreeNode root;


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

    /* ------------------

    /**
     * 哈夫曼编码表
     */
    private Map<Byte, String> codeTable = new HashMap<>();

    /**
     * 生成哈夫曼编码
     */
    public Map<Byte, String> getCodeTable(TreeNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", new StringBuffer());
        return codeTable;
    }

    /**
     * 生成哈夫曼编码列表
     *
     * @param node
     * @param code 路径 左子结点是0 右子结点是1
     * @param sb
     */
    private void getCodes(TreeNode node, String code, StringBuffer sb) {
        if (node == null) {
            return;
        }
        // 用一个新的sbb变量，是为了保存父结点的路径
        StringBuffer sbb = new StringBuffer(sb);
        sbb.append(code);
        // 判断是否是叶子结点，假如是叶子结点，需要将这个结点和路径放到Map里
        if (node.getLeft() == null && node.getRight() == null) {
            // 是叶子结点，需要将这个结点和路径放到Map里
            codeTable.put(node.getData(), sbb.toString());
        } else {
            // 不是叶子结点，递归左子树和右子树
            getCodes(node.getLeft(), "0", sbb);
            getCodes(node.getRight(), "1", sbb);
        }
    }

    /**
     * 将字符串生成哈夫曼编码的列表 也就是byte[]数组
     */
    private byte[] zip(byte[] bytes, Map<Byte, String> codeTable) {
        if (bytes == null) {
            return null;
        }

        // 遍历原始bytes数组去查询 编码表
        StringBuffer sb = new StringBuffer();
        for (byte data : bytes) {
            sb.append(codeTable.get(data));
        }
        // 将编码后的 字节码序列 转换为byte数组

        // 创建返回byte[]数组
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] newBytes = new byte[len];
        // 将字节码每8位转为byte
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String singleByte;
            if (i + 8 < sb.length()) {
                singleByte = sb.substring(i, i + 8);
            } else {
                singleByte = sb.substring(i);
            }
            newBytes[index++] = (byte) Integer.parseInt(singleByte, 2);
        }
        return newBytes;
    }


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
