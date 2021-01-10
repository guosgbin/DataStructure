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
 *      二叉树叶子结点个数 √ 打印叶子结点 √
 *      二叉树度为1结点个数 √  打印度为1的节点 √
 *      输出二叉树中从每个叶子结点到根结点的路径
 *      二叉树第k层的结点个数 √
 *      第k层上叶子结点的个数 √
 *      二叉树的所有左右子树进行交换 √
 *      判断一颗二叉树是否是完全二叉树 √
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
     *      如果二叉树为空，则结点数为0，否则二叉树的结点数等于左子树和右子树的结点之和加1
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
     * 如果二叉树为空，则叶子数为0
     * 如果根的左右子树都为空，则叶子数为1
     * 否则求左子树的叶子数和右子树叶子数之和
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
     * 如果二叉树为空，则深度为0
     * 否则为根的左右子树的深度最大值加1
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

    /**
     * 度为1结点的个数
     */
    public Integer onlyOneSubNodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if ((node.getLeft() != null && node.getRight() == null)
                || (node.getLeft() == null && node.getRight() != null)){
            // 加1之后 继续向下寻找是否有叶子结点
            return 1 + onlyOneSubNodeCount(node.getLeft()) + onlyOneSubNodeCount(node.getRight());
        }
        return onlyOneSubNodeCount(node.getLeft()) + onlyOneSubNodeCount(node.getRight());
    }

    /**
     * 打印度为1的结点
     */
    public void printOnlyOneSubNode(TreeNode node) {
        if (node == null) {
            return;
        }
        if ((node.getLeft() != null && node.getRight() == null)
                || (node.getLeft() == null && node.getRight() != null)){
            // 继续向下寻找是否有度为1的结点
            System.out.print(node);
        }
        printOnlyOneSubNode(node.getLeft());
        printOnlyOneSubNode(node.getRight());
    }

    /**
     * 输出二叉树中从每个叶子结点到根结点的路径
     *
     * 使用一个数组保存已访问的路径
     * 1.当T是空节点，返回上一层，不做处理
     * 2.当T是叶子节点,先将T加入路径中，在输出路径
     * 3.当T不是叶子节点也不是空节点的时候,将该节点加入路径中
     * 4.path中存储从根节点到叶子节点的路径，pathLength是path的长度
     */
    public void printLeafNodeRoute() {
        printLeafNodeRoute(root, new ArrayList(), 0);

    }

    /**
     *  二叉树递归中，程序运行时每个节点会经过三次
     *
     *  TODO ???????????????????????
     */
    private void printLeafNodeRoute(TreeNode node, ArrayList path, int pathlen) {
        if (node == null) {
            return;
        }
        path.add(node.element);
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.print("叶子结点" + node + "的路径: ");
            for (int i = pathlen; i >= 0 ; i--) {
                System.out.print(path.get(i));
            }
            System.out.println();
        }
        printLeafNodeRoute(node.getLeft(), path, pathlen + 1);
        printLeafNodeRoute(node.getRight(), path, pathlen + 1);
        path.remove(pathlen--);
        // pathlen--运行时，是程序最后一次经过此节点，因此应该把它从路径栈移除
        pathlen--;
    }


    /**
     * 输出二叉树中从每个叶子结点到根结点的路径
     */
    public List<String> binTreePath1(TreeNode root) {
        LinkedList<String> l = new LinkedList<String>();
        if(root==null) return l;//如果为空，返回空
        getPath(root,l,root.element+"");
        return l;
    }


    private void getPath(TreeNode root, List<String> re, String s) {
        if(root.left==null && root.right==null) {//此时为叶节点，把s添加到结果列表中，返回
            re.add(s+"");
            return ;
        }
        if(root.left!=null)//左子树不为空，递归
            getPath(root.left,re,s+"->"+root.left.element);
        if(root.right!=null)//右子树不为空，递归
            getPath(root.right,re,s+"->"+root.right.element);
    }

    /**
     * 输出二叉树中从每个叶子结点到根结点的路径
     */
    public   List<String> binTreePath2(TreeNode root) {
        LinkedList<String> l = new LinkedList<String>();
        if(root==null)return l;
        if(root.left==null && root.right==null) {
            l.add(root.element+"");
            return l;
        }
        for (String s : binTreePath2(root.left)) {
            l.add(root.element+"->"+s);
        }
        for (String s : binTreePath2(root.right)) {
            l.add(root.element+"->"+s);
        }
        return l;
    }


    /**
     * 二叉树第k层的结点个数
     *
     * 思路: 获取第k层结点个数,做个递归展示
     *
     *
     * 第k层结点个数: 可视为根节点的左子结点的k-1层结点个数，加上根结点的右子结点的k-1层结点个数
     *
     * eg.
     *       A
     *     /   \
     *    B     C
     *   /     / \
     *  D     E   F
     *  比如说，求第3层结点的个数，就可以视为3-1=2层的B结点和C结点的子结点个数的总和
     *    B     C
     *   /     / \
     *  D     E   F
     *
     *  递归退出条件1 node == null，说明该条路径深度小于k。
     *  递归退出条件2: level == 1，
     */
    public Integer getNodeCountByLevel(TreeNode node, int k) {
        if (k < 1) {
            throw new RuntimeException("层数错误");
        }
        if (node == null) {
            // case 1: node为null，说明k-1层 没有左子结点或右子结点，返回0
            // case 2: 还没到第k-1层，但是没有左子结点或右子结点
            return 0;
        }
        if (k == 1) {
            // k == 1 表明k-1层的左子结点或右子结点存在，因为上面node已经判空了
//            System.out.println(node);
            return 1;
        }
        return getNodeCountByLevel(node.getLeft(), k - 1) + getNodeCountByLevel(node.getRight(), k - 1);
    }

    /**
     * 第k层上叶子结点的个数
     */
    public Integer getLeafNodeCountByLevel(TreeNode node, int k) {
        if (k < 1) {
            throw new RuntimeException("层数错误");
        }
        if (node == null) {
            return 0;
        }
        if (k == 1) {
            if (node.getLeft() == null && node.getRight() == null) {
//                System.out.println(node);
                return 1; // 此结点是叶子结点
            }
            return 0; // 此结点不是叶子结点
        }
        return getLeafNodeCountByLevel(node.getLeft(), k - 1) + getLeafNodeCountByLevel(node.getRight(), k - 1);
    }


    /**
     * 二叉树的所有左右子树进行交换 递归
     */
    public void invertTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.getRight();
        node.setRight(node.getLeft());
        node.setLeft(temp);
        invertTree(node.getLeft());
        invertTree(node.getRight());
    }

    /**
     * 二叉树的所有左右子树进行交换 循环，栈存储（DFS，非递归)
     * 左右节点进行交换，循环翻转每个节点的左右子节点，
     * 将未翻转的子节点存入栈中，循环直到栈里所有节点都循环交换完为止。
     */
    public void invertTreeDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.getRight();
            node.setRight(node.getLeft());
            node.setLeft(temp);
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }
    }

    /**
     * 二叉树的所有左右子树进行交换
     * 循环，队列存储（BFS，非递归）
     */
    public void invertTreeBFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(left);
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }

    /**
     * 判断一个二叉树是否是完全二叉树
     * 二叉树的概念：
     *    除了最后一层，其他层都是充满的，而且最后一层的叶子节点在左边连续，倒数第二层的叶子节点在右边连续
     *
     * 我们可以根据完全二叉树的定义，按照层序遍历一颗树
     * 当遇到空结点时如果这棵树已经遍历完毕，则这棵树就是完全二叉树
     * 如果遇到空结点的后面还有元素则这棵树就不是完全二叉树。
     *
     * 我们可以用一个flag来标记，当遇到第一个空结点是标记为1，如果后面要是再遇到非空结点时就表示不是完全二叉树。
     */
    public boolean isCompleteBinaryTree(TreeNode node) {
        if (node == null) {
            return false;
        }
        boolean flag = false; // true表示已经遇到了度不为2的结点
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            // 出队
            TreeNode head = queue.poll();
            if (head.getRight() != null && head.getLeft() == null) {
                // 有右孩子必定有左孩子
                return false;
            }
            if (flag && head.left != null) {
                return false;
            }
            if (head.left == null || head.right == null)
                flag = true; // 遇到了度不为2的结点
            // 当前结点的左孩子不为空
            if (head.left != null)
                queue.add(head.left);
            // 当前结点的右孩子不为空
            if (head.right != null)
                queue.add(head.right);
        }
        return true;
    }

    /**
     * 结合层次遍历，将为null的结点也放到队列中，当将所有的结点放到队列中后
     * 当遍历到某个结点的元素域的值为null时，退出第一个循环，此时队列中还有元素
     * 假如是完全二叉树，剩下的结点中都是元素域为null的结点，否则不是完全二叉树
     *
     *           曹操
     *        /       \
     *      曹丕       曹植
     *     /    \    /    \
     *   曹睿    曹协 n    曹志
     *  /  \    /  \     /  \
     * n   曹芳 n    n   n    n
     *     /  \
     *    n    n
     */
    public boolean isCompleteBinaryTree2(TreeNode node) {
        if (node == null) {
            return false;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>(); // 此队列不允许增加null结点，可以添加值为null的结点
        queue.offer(node);
        // 遇到第一个null结点是退出循环
        while (node.getElement() != null) {
            // 获取队首元素
            TreeNode head = queue.poll();
            // 将结点的左右子结点入队，空结点也入队
            queue.offer(head.getLeft() != null ? head.getLeft() : new TreeNode(null));
            queue.offer(head.getRight() != null ? head.getRight() : new TreeNode(null));
            // node赋值为新的队首元素
            node = queue.peek();
        }
        // 将队列中剩下的元素依次出队，假如有一个不是null，则不是完全二叉树
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.getElement() != null) {
                return false;
            }
        }
        return true;
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
