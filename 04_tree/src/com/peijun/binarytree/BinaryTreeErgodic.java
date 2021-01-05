package com.peijun.binarytree;

import java.util.Arrays;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/4 21:17
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 二叉树遍历测试
 */
public class BinaryTreeErgodic {
    public static void main(String[] args) {
        // 手动创建一个二叉树
//        BinaryTree<String> binaryTree = initBinaryTree();

        /* ------ 开始测试 遍历 ------*/

        // 前序遍历
//        binaryTree.preOrder();
        // 中序遍历
//        binaryTree.inOrder();
        // 后序遍历
//        binaryTree.postOrder();
        // 层次遍历
//        binaryTree.levelOrder();

        /* ------ 开始测试 创建 ------*/
//        BinaryTree binaryTree = new BinaryTree();
//        binaryTree.createBinaryTree("A,B,D,#,#,E,#,#,C,F,#,#");
//        binaryTree.createBinaryTree("1_曹操,2_曹丕,4_曹睿,#,7_曹芳,#,#,5_曹协,#,#,3_曹植,#,6_曹志,#,#");
        // 前序遍历创建的二叉树试下
//        binaryTree.preOrder();
//        binaryTree.inOrder();
//        // 后序遍历
//        binaryTree.postOrder();
//        // 层次遍历
//        binaryTree.levelOrder();

        /* ------ 开始测试 非递归遍历 ------*/
        BinaryTree<String> binaryTree = initBinaryTree();
        binaryTree.preOrder();
        System.out.println("=====");
        binaryTree.preOrderStack();
        System.out.println("=====");
        binaryTree.preOrderStack2();

    }

    /**
     * 手动创建一个二叉树，直观图如下
     *
     *          曹操
     *        /     \
     *      曹丕     曹植
     *     /   \       \
     *   曹睿   曹协     曹志
     *      \
     *      曹芳
     */
    private static BinaryTree<String> initBinaryTree() {
        // 在遍历前，先在内存中创建一个二叉树出来，先用呆板的创建方法
        BinaryTree<String> binaryTree = new BinaryTree<>();
        // 设置根节点等结点
        BinaryTree.TreeNode<String> root = new BinaryTree.TreeNode<>("1_曹操");
        BinaryTree.TreeNode<String> node2 = new BinaryTree.TreeNode<>("2_曹丕");
        BinaryTree.TreeNode<String> node3 = new BinaryTree.TreeNode<>("3_曹植");
        BinaryTree.TreeNode<String> node4 = new BinaryTree.TreeNode<>("4_曹睿");
        BinaryTree.TreeNode<String> node5 = new BinaryTree.TreeNode<>("5_曹协");
        BinaryTree.TreeNode<String> node6 = new BinaryTree.TreeNode<>("6_曹志");
        BinaryTree.TreeNode<String> node7 = new BinaryTree.TreeNode<>("7_曹芳");
        // 手动设置结点之间的关系
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node4.setRight(node7);
        binaryTree.setRoot(root);
        return binaryTree;
    }


}
