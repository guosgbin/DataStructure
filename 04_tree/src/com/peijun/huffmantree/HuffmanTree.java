package com.peijun.huffmantree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/21 23:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 哈夫曼树
 */
public class HuffmanTree {

    private TreeNode root;


    /**
     * 给定一个int数组，构造一个哈夫曼树
     * 使用List集合，但是需要自己排序
     * {19,21,2,3,6,7,10,32}
     *
     * @param array
     * @return
     */
    public void buildHuffmanTree1(int[] array) {
        // 初始化结点
        ArrayList<TreeNode> nodes = Arrays.stream(array)
                .mapToObj(TreeNode::new)
                .collect(Collectors.toCollection(ArrayList::new));

        // 当nodes集合中只剩下最后一个结点时，表明哈夫曼树构建成功
        while (nodes.size() > 1) {
            // 先将集合按照从小到大排序
            Collections.sort(nodes);
            // 拿出最小的两个元素,并将其删除
            TreeNode left = nodes.remove(0);
            TreeNode right = nodes.remove(0);
            // 创建一个新的结点
            TreeNode father = new TreeNode(left.weight + right.weight);
            father.left = left;
            father.right = right;
            // 将新的结点放入集合中
            nodes.add(father);
        }
        // 哈夫曼树的根结点
        root = nodes.remove(0);
    }

    /**
     * 给定一个int数组，构造一个哈夫曼树
     * <p>
     * 什么 还要用List自己排序，太麻烦了，使用优先队列{@linkplain java.util.PriorityQueue}吧
     * {19,21,2,3,6,7,10,32}
     *
     * @param array
     * @return
     */
    public void buildHuffmanTree2(int[] array) {
        // 初始化结点 放到优先队列中,添加元素到该队列会自动按照实现的Comparable接口排序出队
        PriorityQueue<TreeNode> queue = Arrays.stream(array)
                .mapToObj(TreeNode::new)
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (queue.size() > 1) {
            // 拿出两个元素
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // 创建一个新的结点
            TreeNode father = new TreeNode(left.weight + right.weight);
            father.left = left;
            father.right = right;
            // 将新的结点放入优先队列中
            queue.offer(father);
        }
        // 哈夫曼树的根结点
        root = queue.poll();
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
         * 该结点表示的字符
         */
        private char value;

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

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public TreeNode(int weight) {
            this.weight = weight;
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
