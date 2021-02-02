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
//        testString();
        testZip();
    }

    private static void testZip() {
        HuffmanCode huffmanCode = new HuffmanCode();

        // 压缩文件
//        String src = "D:\\笔记模块\\GitHub开始\\数据结构和算法\\数据结构_013_哈夫曼树\\压缩使用文件\\picture.png";
//        String dst="D:\\笔记模块\\GitHub开始\\数据结构和算法\\数据结构_013_哈夫曼树\\压缩使用文件\\pic.zip";
//        huffmanCode.zipFile(src, dst);
//        System.out.println("压缩完成");

        // 解压文件
        String src = "D:\\笔记模块\\GitHub开始\\数据结构和算法\\数据结构_013_哈夫曼树\\压缩使用文件\\pic.zip";
        String dst="D:\\笔记模块\\GitHub开始\\数据结构和算法\\数据结构_013_哈夫曼树\\压缩使用文件\\picUnZip.png";
        huffmanCode.unZipFile(src,dst);
        System.out.println("解压完成");
    }


    private static void testString() {
//        String sentence = "huang gang jvav";
        String sentence = "i like like like java do you like a java";

        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] zip = huffmanCode.zip(sentence.getBytes());
        System.out.println(Arrays.toString(zip));

        byte[] decode = huffmanCode.unZip(huffmanCode.getCodeTable(), zip);

//        String str = huffmanCode.unZip(zip);

        System.out.println(new String(decode));

//        System.out.println(str);
    }
}