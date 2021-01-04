package com.peijun.stack.application;

import java.util.Stack;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/17 9:00
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 进制转换
 */
public class BaseConversion {

    private static String[] numArray = {
            "0","1","2","3","4","5","6","7",
            "8","9","a","b","c","d","e","f"};

    public static void main(String[] args) {
        System.out.println(conversion(139, 8));
        System.out.println(Integer.toOctalString(139));
    }

    /**
     * 转换指定进制
     *
     * @param num   指定十进制数
     * @param radix 待转换的进制
     * @return
     */
    private static String conversion(int num, int radix) {
        if (radix <= 0) {
            throw new RuntimeException("进制有误");
        }
        // 使用JDK自带的栈
        Stack<String> stack = new Stack<>();
        // 当num为0时退出，因为在最后一次除法中结果为0
        while (num != 0) {
            // 作除法，余数入栈，得到的商继续求值
            stack.push(numArray[num % radix]);
            num /= radix;

        }

        // 将栈中的元素依次弹栈
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


}
