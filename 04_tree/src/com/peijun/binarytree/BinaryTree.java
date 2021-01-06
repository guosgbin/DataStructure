package com.peijun.binarytree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/4 21:22
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 二叉树对象
 *
 * 成员方法：
 *      前序遍历 递归和非递归
 *      中序遍历 递归和非递归
 *      后序遍历 递归和非递归
 *      层次遍历
 *      创建二叉树
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
            inOrder(node.getRight());
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
    private void postOrder(TreeNode node) {
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

    /**
     * 创建二叉树 默认是字符串等基本类型
     *
     * 输入一个 补空二叉树 的前序序列 来创建二叉树
     */
    public void createBinaryTree(String preOrderStr) {
        // 创建一个扫描对象
        String[] split = preOrderStr.split(",");
        List<String> preOrderList = Arrays.stream(split).collect(Collectors.toList());
        Iterator<String> it = preOrderList.iterator();
        this.root = createBinaryTree(it);
    }

    private TreeNode<E> createBinaryTree(Iterator<String> it) {
        if (!it.hasNext()) {
            return null;
        }
        String temp = it.next();
        if (Objects.equals(temp, "#")) {
            return null;
        }
        // 生成父节点
        TreeNode<E> node = new TreeNode<>((E)temp); // 针对基本数据类型类转换的
        // 生成左子树
        node.setLeft(createBinaryTree(it));
        // 生成右子树
        node.setRight(createBinaryTree(it));
        return node;
    }

    /**
     * 非递归前序遍历 方法3  --记这个--
     */
    public void preOrderStack() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> node = root;
        while(node != null || !stack.isEmpty()){
            if (node != null){
                System.out.println(node); // 前序遍历
                stack.push(node);
                node = node.getLeft(); // 获取左子结点
            } else {
                // 弹栈是因为没找到当前结点的左子结点，来找当前结点的右子结点
                node = stack.pop();
                node = node.getRight(); // 获取右子结点
            }
        }
    }

    /**
     * 非递归前序遍历 2
     * 1.首先将root压栈；
     * 2.每次从堆栈中弹出栈顶元素，表示当前访问的元素，对其进行打印；
     * 3.依次判断其右子树，左子树是否非空，并进行压栈操作，至于为什么先压栈右子树，因为先压栈的后弹出，左子树需要先访问，因此后压栈；
     */
    public void preOrderStack2() {
        if(root == null) {
            // 根结点为null直接返回
            return;
        }

        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root); // 首先将根结点压栈

        while(!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            System.out.println(current); // 打印弹栈元素

            if(current.right != null) {
                // 先右子结点压栈，后打印
                stack.push(current.right);
            }

            if(current.left != null) {
                // 左子结点压栈
                stack.push(current.left);
            }
        }
    }

    /**
     * 非递归中序遍历 2
     */
    public void inOrderStack() {
        if(root == null) {
            // 根结点为空则直接返回
            return;
        }
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> node = root;
        // 循环退出条件 当前结点为空或栈为空
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                //
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.println(node); // 中序遍历
                node = node.getRight();
            }
        }
    }

    /**
     * 非递归后序遍历 --记这个--
     */
    public void postOrderStack(){
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        TreeNode<E> node = root;
        TreeNode<E> preNode = null;   // 记录之前遍历的右结点
        while(node != null || !stack.isEmpty()){
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.peek();
                // 如果右结点为空，或者右结点之前遍历过，打印根结点
                if(node.getRight() == null || node.getRight() == preNode){
                    System.out.println(node);
                    node = stack.pop();
                    preNode = node;
                    node = null;
                }
                else{
                    node = node.getRight();
                }
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
