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
    public void inThread(ThreadedNode<E> node) {
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
