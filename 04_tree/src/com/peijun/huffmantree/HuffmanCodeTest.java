package com.peijun.huffmantree;

import java.util.Arrays;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/28 22:25
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 哈夫曼编码 测试类
 */
public class HuffmanCodeTest {

    public static void main(String[] args) {
//        String sentence = "huang gang jvav";
        String sentence = "i like like like java do you like a java";

        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] zip = huffmanCode.zip(sentence.getBytes());
        System.out.println(Arrays.toString(zip));

        byte[] decode = huffmanCode.decode(huffmanCode.getCodeTable(), zip);

        System.out.println(new String(decode));
    }
}