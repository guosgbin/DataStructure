package com.peijun.binarytree;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/4 21:17
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 二叉树遍历测试
 */
public class BinaryTreeErgodic {
    private static final String LINE = System.lineSeparator() + "===================";

    /**
     *       A
     *     /   \
     *    B     C
     *   / \   /
     *  D   E F
     */
    private static final String TREE_1 = "A,B,D,#,#,E,#,#,C,F,#,#,#";

    /**
     *          曹操
     *        /     \
     *      曹丕     曹植
     *     /   \       \
     *   曹睿   曹协     曹志
     *      \
     *      曹芳
     */
    private static final String TREE_2 = "1_曹操,2_曹丕,4_曹睿,#,7_曹芳,#,#,5_曹协,#,#,3_曹植,#,6_曹志,#,#";

    /**
     *       A
     *     /   \
     *    B     C
     *   /     / \
     *  D     E   F
     *   \         \
     *    G         H
     *   / \
     *  I   J
     */
    private static final String TREE_3 = "A,B,D,#,G,I,#,#,J,#,#,#,C,E,#,#,F,#,H,#,#";

    public static void main(String[] args) {
        // 开始测试 递归遍历
//        testRecursionErgodic();
        // 测试创建二叉树
//        testCreateTree();
        // 测试非递归遍历二叉树
//        testNotRecursionErgodic();
        // 测试二叉树的其他操作
        testOtherOption();


    }

    /**
     * 测试二叉树的其他操作
     */
    private static void testOtherOption() {
        // 初始化树
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree(TREE_3);

//        // 二叉树总结点个数
//        System.out.println("二叉树总结点个数: " + binaryTree.nodeCount(binaryTree.getRoot()));
//        // 二叉树叶子结点个数
//        System.out.println("二叉树叶子结点个数: " + binaryTree.leafNodeCount(binaryTree.getRoot()));
//        // 二叉树深度
//        System.out.println("二叉树深度: " + binaryTree.treeDepth(binaryTree.getRoot()));
//        // 打印二叉树叶子结点
//        System.out.print("二叉树叶子结点: ");
//        binaryTree.printLeafNode(binaryTree.getRoot());
//        System.out.println();
//
//        // 二叉树度为1结点的个数
//        System.out.println("二叉树度为1结点的个数: " + binaryTree.onlyOneSubNodeCount(binaryTree.getRoot()));
//        // 打印二叉树度为1结点
//        System.out.print("二叉树度为1结点: ");
//        binaryTree.printOnlyOneSubNode(binaryTree.getRoot());
//        System.out.println();

        // 打印二叉树所有叶子结点到根的路径
        List list = binaryTree.binTreePath2(binaryTree.getRoot());
        for (Object o : list) {
            System.out.println(o);
        }

    }

    /**
     * 测试非递归遍历
     */
    public static void testNotRecursionErgodic() {
//        BinaryTree<String> binaryTree = initBinaryTree();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree(TREE_3);
//        binaryTree.preOrder();
//        System.out.println(LINE);
//        binaryTree.preOrderStack();
//        System.out.println(LINE);
//        binaryTree.preOrderStack2();

//        binaryTree.inOrder();
//        System.out.println(LINE);
//        binaryTree.inOrderStack();

        binaryTree.postOrder();
        System.out.println(LINE);
        binaryTree.postOrderStack();
        System.out.println(LINE);
        binaryTree.postOrderStack2();
    }

    /**
     * 测试创建二叉树
     */
    public static void testCreateTree() {
        /* ------ 开始测试 创建 ------*/
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree(TREE_3);

        // 前序遍历创建的二叉树试下
        binaryTree.preOrder();
//        binaryTree.inOrder();
//        // 后序遍历
//        binaryTree.postOrder();
//        // 层次遍历
//        binaryTree.levelOrder();
    }

    /**
     * 测试递归遍历
     */
    public static void testRecursionErgodic() {
        // 手动创建一个二叉树
//        BinaryTree<String> binaryTree = initBinaryTree();
        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.createBinaryTree(TREE_3);
        // 前序遍历
        binaryTree.preOrder();
        System.out.println(LINE);
        // 中序遍历
        binaryTree.inOrder();
        System.out.println(LINE);
        // 后序遍历
        binaryTree.postOrder();
        System.out.println(LINE);
        // 层次遍历
        binaryTree.levelOrder();
        System.out.println(LINE);
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
        node3.setRight(node6);
        node4.setRight(node7);
        binaryTree.setRoot(root);
        return binaryTree;
    }


}
