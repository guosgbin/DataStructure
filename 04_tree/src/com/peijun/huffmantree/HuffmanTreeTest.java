package com.peijun.huffmantree;

import java.util.PriorityQueue;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/23 0:06
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 哈夫曼树和哈夫曼编码测试类
 */
public class HuffmanTreeTest {

    private static final int[] TREE_1 = {19,21,2,3,6,7,10,32};

    public static void main(String[] args) {
        // 构建哈夫曼树
//        buildHuffmanTree();
    }

    /**
     * 测试哈夫曼树
     */
    private static void buildHuffmanTree() {
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildHuffmanTree2(TREE_1);

        System.out.println("===");
    }


}
