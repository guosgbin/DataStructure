package com.peijun.stack.application;

import java.util.Objects;
import java.util.Stack;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/19 9:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 回文
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(palindrome("傻逼产品1品产逼傻"));
    }

    public static boolean palindrome(String str) {
        if(null == str || Objects.equals(str, "")) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        int i = 0;
        for (; i < chars.length / 2; i++) {
            stack.push(chars[i]);
        }

        // 如果是奇数 则中间的哪一个元素不用判断了
        if (chars.length % 2 == 1) {
            i++;
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != chars[i]) {
                return false;
            } else {
                i++;
            }
        }

        return true;
    }
}
