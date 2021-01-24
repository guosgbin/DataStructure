package com.peijun.huffmantree;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/24 23:04
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 哈夫曼编码
 */
public class HuffmanCode {

    // nobody knows China better than me
    public static void main(String[] args) {

    }

    /**
     * 获取每个字符和其权值(出现次数)的结点列表
     * @param bytes
     */
    private void getNodeList(byte[] bytes) {

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
        private byte data;

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

        public byte getData() {
            return data;
        }

        public void setData(byte data) {
            this.data = data;
        }

        public TreeNode(int weight, byte data) {
            this.weight = weight;
            this.data = data;
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
