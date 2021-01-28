package com.peijun.huffmantree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//
//        HuffmanCode huffmanCode = new HuffmanCode();
//        byte[] zip = huffmanCode.zip(sentence.getBytes());
//        System.out.println(Arrays.toString(zip));


        byte aByte = (byte) -2; // -2 (signed) and 254 (unsigned)
        System.out.println("Input : " + aByte);

        // byte to an unsigned integer
        // & 0xff to prevent sign extension, no effect on positive
        int result = aByte & 0xff;

        System.out.println(result); // 254
        System.out.println(Integer.toBinaryString(result)); // 1111 1110

        String resultWithPadZero = String.format("%32s", Integer.toBinaryString(result))
                .replace(" ", "0");

        System.out.println(resultWithPadZero); // 00000000000000000000000011111110
        System.out.println(printBinary(resultWithPadZero, 8, "|")); // 00000000|00000000|00000000|11111110

    }

    // pretty print binary with separator
    public static String printBinary(String binary, int blockSize, String separator) {

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));

    }
}