package com.peijun.binarytree;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/14 0:26
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 线索二叉树
 */
public class ThreadedBinaryTree<E> {
    /**
     * 根结点
     */
    private ThreadedNode<E> root;

    /**
     * 线索化二叉树辅助指针,指向当前结点的前驱
     */
    private ThreadedNode<E> pre;

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(ThreadedNode<E> root) {
        this.root = root;
    }

    public ThreadedNode<E> getRoot() {
        return root;
    }

    public void setRoot(ThreadedNode<E> root) {
        this.root = root;
    }

    /**
     * 中序线索化二叉树
     */
    public void inThread() {
        if (root == null) {
            return;
        }
        pre = null;
        inThread(root);
        if (pre != null) {
            // 处理最后一个结点 因为最后一个结点的状态未设置就退出了
            pre.setRightTag(1); // 设置为1是因为最后一个结点的右指针肯定指向null的
            pre.setRight(null);
        }
    }

    private void inThread(ThreadedNode<E> node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null) {
            inThread(node.getLeft());
        }
        if (node.getLeft() == null) {
            // 当前结点的左指针为空，使该指针指向前驱，即pre
            node.setLeft(pre);
            node.setLeftTag(1); // 修改指针类型
        }
        if (pre != null && pre.getRight() == null) {
            // 当前结点的前驱结点的右指针为空，使该指针指向其后驱，即node
            pre.setRight(node);
            pre.setRightTag(1); // 修改指针类型
        }
        pre = node; // 保持pre指向p的前驱
//        System.out.println(node);
        if (node.getRight() != null) {
            inThread(node.getRight());
        }
    }


    /**
     * 中序遍历线索二叉树  递归
     * @param node
     */
    public void inOrder01(ThreadedNode<E> node) {
        if (node == null) {
            return;
        }
        if (node.getLeftTag() != 1) {
            inOrder01(node.getLeft());
        }
        System.out.println(node);
        if (node.getRightTag() != 1) {
            inOrder01(node.getRight());
        }
    }

    /**
     * 中序遍历线索二叉树 非递归
     * 思路：
     *  1.首先找到最左边的结点，此结点是中序遍历的第一个结点
     *  2.打印结点
     *  3.因为线索化二叉树的指针可能会指向后继结点，
     *      所以沿着右索引查找当前结点的后继结点并打印，直到rightTag == 0
     *  4.遍历当前结点的右子树
     *  循环往复
     */
    public void inOrder02() {
        ThreadedNode<E> node = root; // 获取根结点
        while (node != null) {
            // 首先找到最左边的结点
            while (node.getLeftTag() == 0) {
                node = node.getLeft();
            }
            // 打印结点
            System.out.println(node);
            // 判断当前结点的右指针是否指向后继结点
            while (node.getRightTag() == 1 && node.getRight() != null) {
                // 循环条件，右指针指向的结点是当前结点的后继结点，直接打印
                node = node.getRight(); // 访问后继结点
                System.out.println(node);
            }
            // 遍历右子树
            node = node.getRight();
        }
    }

    /**
     * 中序遍历线索二叉树  非递归
     */
    public void inOrder03() {
        ThreadedNode<E> node = root;
        for(node = firstNode(node); node != null; node = nextNode(node)) {
            System.out.println(node);
        }
    }

    /**
     * 找到某个结点 中序遍历的 第一个访问的结点
     * @param node
     * @return
     */
    private ThreadedNode<E> firstNode(ThreadedNode<E> node) {
        if (node == null) {
            return null;
        }
        // 直到找到结点的左指针标记为1的，说明就是最左结点了(但是不一定是叶子结点)
        while (node.getLeftTag() == 0) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * 找到某个结点的下一个结点
     * 假如当前结点的rightTag标志为 1 说明其指向后继结点 直接返回
     * 假如当前结点的rightTag标志为 0 说明其指向右子结点 返回右子结点的最左结点
     *
     * @param node
     * @return
     */
    private ThreadedNode<E> nextNode(ThreadedNode<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getRightTag() == 1) {
            return node.getRight();
        } else {
            // 访问右结点的 后继   右子树最左下的结点
            return firstNode(node.getRight());
        }
    }

    /**
     * 反向中序遍历 线索二叉树
     */
    public void inOrderReverse() {
        ThreadedNode<E> node = root;
        for(node = lastNode(node); node != null; node = preNode(node)) {
            System.out.println(node);
        }
    }

    /**
     * 找到中序最后一个结点
     * 中序遍历的 最后一个结点 是 最右边的结点  不一定是叶子结点
     */
    private ThreadedNode<E> lastNode(ThreadedNode<E> node) {
        if (node == null) {
            return null;
        }
        while (node.getRightTag() == 0) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * 找到中序遍历  的上一个节点
     * 假如当前结点的leftTag标志为 1 说明其指向前驱结点 直接返回
     * 假如当前结点的leftTag标志为 0 说明其指向左子结点 返回左子结点的最右结点
     */
    private ThreadedNode<E> preNode(ThreadedNode<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeftTag() == 1) {
            return node.getLeft();
        } else {
            return lastNode(node.getLeft());
        }
    }

    /**
     * 笨比方法 手动创建一个二叉树，直观图如下
     *
     *          曹操
     *        /     \
     *      曹丕     曹植
     *     /   \       \
     *   曹睿   曹协     曹志
     *      \
     *      曹芳
     */
    public static ThreadedBinaryTree<String> initThreadedTree() {
        // 在遍历前，先在内存中创建一个二叉树出来，先用呆板的创建方法
        ThreadedBinaryTree<String> binaryTree = new ThreadedBinaryTree<>();
        // 设置根节点等结点
        ThreadedNode<String> root = new ThreadedNode<>("1_曹操");
        ThreadedNode<String> node2 = new ThreadedNode<>("2_曹丕");
        ThreadedNode<String> node3 = new ThreadedNode<>("3_曹植");
        ThreadedNode<String> node4 = new ThreadedNode<>("4_曹睿");
        ThreadedNode<String> node5 = new ThreadedNode<>("5_曹协");
        ThreadedNode<String> node6 = new ThreadedNode<>("6_曹志");
        ThreadedNode<String> node7 = new ThreadedNode<>("7_曹芳");
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


    /**
     * 线索化结点
     */
    private static class ThreadedNode<E>{
        /**
         * 结点元素域
         */
        private E element;
        /**
         * 左子树指针
         */
        private ThreadedNode<E> left;
        /**
         * 右子树指针
         */
        private ThreadedNode<E> right;
        /**
         * 0表示指向的是左子结点  默认0
         * 1表示指向前驱结点
         */
        private int leftTag;
        /**
         * 0表示指向是右子结点  默认0
         * 1表示指向后继结点
         */
        private int rightTag;

        public ThreadedNode(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public ThreadedNode<E> getLeft() {
            return left;
        }

        public void setLeft(ThreadedNode<E> left) {
            this.left = left;
        }

        public ThreadedNode<E> getRight() {
            return right;
        }

        public void setRight(ThreadedNode<E> right) {
            this.right = right;
        }

        public int getLeftTag() {
            return leftTag;
        }

        public void setLeftTag(int leftTag) {
            this.leftTag = leftTag;
        }

        public int getRightTag() {
            return rightTag;
        }

        public void setRightTag(int rightTag) {
            this.rightTag = rightTag;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
