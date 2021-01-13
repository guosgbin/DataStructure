package com.peijun.binarytree;

import java.util.Stack;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/10 18:20
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 顺序存储二叉树  一般针对的是完全二叉树
 *
 * 由完全二叉树的性质  假如根结点的索引为0，
 * 假如某个父节点的索引为n，若它的左右子结点存在的话，那么
 *  左子结点的索引为2n+1
 *  右子结点的索引为2n+2
 *
 * 要求 传入一个数组，按照前中后的方式遍历它
 */
public class ArrayBinaryTree<E> {
    /**
     * 存储元素的数组
     */
    private Object[] elementData;

    public ArrayBinaryTree(Object[] elementData) {
        this.elementData = elementData;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(0);
    }

    /**
     * 前序遍历
     */
    private void preOrder(int index) {
        if (elementData == null || elementData.length <= 0) {
            return;
        }
        // 先打印父结点
        System.out.print(elementData[index]);
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // 递归打印左子树的结点
        if (left < elementData.length) {
            preOrder(left);
        }
        if (right < elementData.length) {
            preOrder(right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(0);
    }

    /**
     * 中序遍历
     */
    private void inOrder(int index) {
        if (elementData == null || elementData.length <= 0) {
            return;
        }
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // 递归打印左子树的结点
        if (left < elementData.length) {
            inOrder(left);
        }
        // 中间印父结点
        System.out.print(elementData[index]);
        if (right < elementData.length) {
            inOrder(right);
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        postOrder(0);
    }

    /**
     * 后续遍历
     */
    private void postOrder(int index) {
        if (elementData == null || elementData.length <= 0) {
            return;
        }
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // 递归打印左子树的结点
        if (left < elementData.length) {
            postOrder(left);
        }
        if (right < elementData.length) {
            postOrder(right);
        }
        // 后打印父结点
        System.out.print(elementData[index]);
    }

}
