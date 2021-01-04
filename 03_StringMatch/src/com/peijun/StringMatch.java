package com.peijun;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/30 0:14
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class StringMatch {
    public static void main(String[] args) {
        String parent = "sonsongpong";
        String matchStr = "song";
        System.out.println("位置：" + bruteForceMatch(parent, matchStr));
    }

    /**
     * BF匹配算法
     *
     * @param parent   主串
     * @param matchStr 待匹配的子串
     * @return
     */
    public static int bruteForceMatch(String parent, String matchStr) {
        // 转换字符数组
        char[] parents = parent.toCharArray();
        char[] matches = matchStr.toCharArray();
        // 获取字符串长度
        int parentLen = parent.length();
        int matchLen = matchStr.length();
        // 开始循环比较 count为比较次数
        int i = 0, j = 0, count = 0;
        while (i < parentLen && j < matchLen) {
            count++; // 比较次数自增
            if (parents[i] == matches[j]) {
                // 两个字符相等，则i和j自增
                i++;
                j++;
            } else {
                // 说明两个字符不相等，i和j指针需要回退
                i = i - j + 1;
                j = 0;
            }
        }
        System.out.printf("总共比较了%s次", count);
        if (j >= matchLen) {
            System.out.println("匹配成功");
            return i - matchLen;
        } else {
            System.out.println("匹配失败");
            return -1;
        }
    }
}
