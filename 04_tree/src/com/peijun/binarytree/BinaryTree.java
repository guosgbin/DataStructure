package com.peijun.binarytree;

import java.util.ArrayDeque;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/4 21:22
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 二叉树对象
 */
public class BinaryTree<E> {
    /**
     * 根结点
     */
    private TreeNode<E> root;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode<E> root) {
        this.root = root;
    }

    /**
     * 前序遍历
     * 1.先访问根节点
     * 2.输出当前结点 （父节点先输出）
     * 3.假如当前结点的左子结点不为空，则递归遍历左子树
     * 4.假如左子树遍历完毕，且当前结点的右子节点不为空，则递归遍历右子树
     */
    public void preOrder() {
        if (root == null) {
            return;
        }
        preOrder(root);

    }

    /**
     * 1_曹操 > 2_曹丕 > 4_曹睿 > 7_曹芳 > 5_曹协 > 3_曹植 > 6_曹志
     */
    private void preOrder(TreeNode node) {
        // 前序遍历，先打印父结点
        System.out.println(node);
        // 假如当前结点的左子结点不为空，继续遍历左子树
        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        // 假如当前结点的右子结点不为空，继续遍历右子树
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    /**
     * 中序遍历
     *  1.先访问根节点
     *  2.假如当前结点的左子结点不为空，则递归遍历左子树
     *  3.输出当前结点 (父节点中间输出)
     *  4.假如左子树遍历完毕，且当前结点的右子节点不为空，则递归遍历右子树
     */
    public void inOrder() {
        if (root == null) {
            return;
        }
        inOrder(root);
    }

    /**
     * 4_曹睿 > 7_曹芳 > 2_曹丕 > 5_曹协 > 1_曹操 > 3_曹植 > 6_曹志
     */
    private void inOrder(TreeNode node) {
        // 假如当前结点的左子结点不为空，继续遍历左子树
        if (node.getLeft() != null) {
            inOrder(node.getLeft());
        }
        // 中序遍历，中间打印父结点
        System.out.println(node);
        // 假如当前结点的右子结点不为空，继续遍历右子树
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            return;
        }
        postOrder(root);
    }

    /**
     * 7_曹芳 > 4_曹睿 > 5_曹协 > 2_曹丕 > 6_曹志 > 3_曹植 > 1_曹操
     */
    public void postOrder(TreeNode node) {
        // 假如当前结点的左子结点不为空，继续遍历左子树
        if (node.getLeft() != null) {
            postOrder(node.getLeft());
        }
        // 假如当前结点的右子结点不为空，继续遍历右子树
        if (node.getRight() != null) {
            postOrder(node.getRight());
        }
        // 后序遍历，最后打印父结点
        System.out.println(node);
    }

    /**
     * 层次遍历
     * 首先遍历第一层，再第二层... 从左至右
     * 使用JDK自带的队列实现层次遍历
     *
     * 1_曹操 > 2_曹丕 > 3_曹植 > 4_曹睿 > 5_曹协 > 6_曹志 > 7_曹芳
     */
    public void levelOrder() {
        if (root == null) {
            // 根为空，直接退出
            return;
        }
        ArrayDeque<TreeNode<E>> deque = new ArrayDeque<>();
        deque.offer(root); // 根节点入队
        while (!deque.isEmpty()) {
            // 队首出队
            TreeNode<E> node = deque.remove();
            System.out.println(node); // 打印当前结点
            TreeNode<E> left = node.getLeft();
            TreeNode<E> right = node.getRight();
            if (left != null) {
                deque.offer(left);
            }
            if (right != null) {
                deque.offer(right);
            }
        }
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }

    /**
     * 二叉树的结点对象
     *
     * @param <E>
     */
    public static class TreeNode<E> {
        /**
         * 结点元素域
         */
        private E element;
        /**
         * 左子树指针
         */
        private TreeNode<E> left;
        /**
         * 右子树指针
         */
        private TreeNode<E> right;

        public TreeNode(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<E> left) {
            this.left = left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public void setRight(TreeNode<E> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return element + "";
        }
    }
}
