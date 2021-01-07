package com.peijun.binarytree;

import javax.swing.tree.TreeNode;
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
 *      前序遍历 递归 √
 *      前序遍历 非递归 √
 *      中序遍历 递归 √
 *      中序遍历 非递归 √
 *      后序遍历 递归 √
 *      后序遍历 非递归 √
 *      层次遍历 √
 *      创建二叉树 √
 *      二叉树的深度 √
 *      二叉树总结点个数 √
 *      二叉树叶子结点个数 √ 打印叶子结点
 *      二叉树度为1结点个数  打印度为1的节点
 *      输出二叉树中从每个叶子结点到根结点的路径
 *      二叉树第k层的结点个数
 *      第k层上叶子结点的个数
 *      二叉树的所有左右子树进行交换
 *      判断一颗二叉树是否是完全二叉树
 *              //两条规则，违反任意一条均不是完全二叉树
 *              //1、某结点无左孩子，则一定没有右孩子
 *              //2、若某结点缺少左孩子或右孩子，则其所有后继（层次遍历的后继）一定无孩子
 *
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
        System.out.print(node);
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
        System.out.print(node);
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
        System.out.print(node);
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
            System.out.print(node); // 打印当前结点
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
     * 非递归前序遍历  --记这个--
     *
     *  1.初始时依次扫描根结点的所有左侧结点，访问它，并将它们一一进栈
     *  2.出栈一个结点
     *  3.扫描该结点的右子结点，并将其进栈
     *  4.依次扫描右子结点的所有左侧结点，并一一进栈
     *  5.反复该过程直到栈为空为止
     */
    public void preOrderStack() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> node = root;
        while(node != null || !stack.isEmpty()){
            if (node != null){
                System.out.print(node); // 前序遍历
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
            System.out.print(current); // 打印弹栈元素

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
     *
     * 1.初始时依次扫描根结点的所有左侧结点，并将它们一一进栈
     * 2.出栈一个结点，访问它
     * 3.扫描该结点的右子结点，并将其进栈
     * 4.依次扫描右子结点的所有左侧结点，并一一进栈
     * 5.反复该过程直到栈为空为止
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
                // case 1 首先一次扫描根结点的所有左子结点，一一进栈
                // case 2 一次扫描右子节点的所有左侧结点，一一进栈
                stack.push(node);
                node = node.getLeft();
            } else {
                // 左子结点为空，或者右子结点为空，出栈一个结点访问它
                node = stack.pop();
                System.out.print(node); // 中序遍历
                node = node.getRight();
            }
        }
    }

    /**
     * 非递归后序遍历 --记这个--
     *
     *  1.初始时依次扫描根结点的所有左侧结点，并将它们一一进栈
     *  2.查看栈顶元素的右结点
     *      2.1 假如该右结点为空，或者已经访问过，则弹出栈顶结点，并访问该结点。
     *      2.1 假如该右节点不为空，或者未访问过，则依次扫描右子结点的所有左侧结点，并一一进栈
     *  3.反复该过程直到栈为空为止
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
                    System.out.print(node);
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

    /**
     * 使用标志
     *
     * 思路
     *  node首先指向root结点
     *  因为是后续遍历，所以不会先打印父结点，需要先将父结点压栈，而不是访问它
     *  当左子树访问完毕后，会再次回到此处，搜索到了该父结点(结点出栈)，此时也不去访问它，需要先去访问右子节点
     *  所以需要再次将该结点入栈。只有当该结点的右子节点访问完毕返回到该结点时，才能访问该结点。
     *
     *  为了标明某个结点是否访问过需要增加一个flag变量，flag=0表示该结点暂时不支持访问，1表示可以访问。
     *  flag的值随同节点一起入栈和出栈。算法中设置了两个堆栈，堆栈1存放结点地址，堆栈2存放flag
     */
    public void postOrderStack2(){
        Stack<TreeNode<E>> stack = new Stack<>();
        Stack<Integer> flagStack = new Stack<>();
        TreeNode<E> node = root;
        int flag = 0; // 标识初始0
        while(node != null || !stack.isEmpty()){
            while (node != null) {
                stack.push(node);
                flagStack.push(0); // 初始0
                node = node.getLeft();
            }
            node = stack.pop(); // 弹栈
            flag = flagStack.pop();
            if (flag == 0) {
                stack.push(node);
                flagStack.push(1);
                node = node.getRight();
            } else {
                System.out.print(node);
                node = null;
            }
        }
    }

    /**
     * 二叉树总结点个数
     *
     * 方式1是定义一个变量，在遍历的时候加1即可
     * 方式2是递归查找结点个数
     *
     * 此代码实现的是方式2 方式1太简单了
     */
    public Integer nodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Integer leftCount = nodeCount(node.getLeft());
        Integer rightCount = nodeCount(node.getRight());
        return  leftCount + rightCount + 1;
    }

    /**
     * 二叉树叶子结点个数
     */
    public Integer leafNodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getRight() == null && node.getLeft() == null) {
            return 1;
        }
        Integer leftCount = leafNodeCount(node.getLeft());
        Integer rightCount = leafNodeCount(node.getRight());
        return  leftCount + rightCount;
    }

    /**
     * 二叉树的深度
     */
    public Integer treeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Integer leftDepth = treeDepth(node.getLeft());
        Integer rightDepth = treeDepth(node.getRight());
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    /**
     * 打印叶子结点
     */
    public void printLeafNode(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.print(node);
        }
        printLeafNode(node.getLeft());
        printLeafNode(node.getRight());
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
