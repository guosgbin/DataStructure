package com.peijun.binarytree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     *  {19,21,2,3,6,7,10,32}
     *
     * @param array
     * @return
     */
    public TreeNode buildHuffmanTree(int[] array) {
        // 初始化结点
        List<TreeNode> nodes = Arrays.stream(array)
                .mapToObj(TreeNode::new)
                .collect(Collectors.toList());

        // 当nodes集合中只剩下最后一个结点时，表明哈夫曼树构建成功
        while (nodes.size() > 1) {
            // 先将集合按照从小到大排序
            Collections.sort(nodes);
            // 拿出最小的两个

        }
        return null;
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
