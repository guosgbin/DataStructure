package com.peijun;

/**
 * @author: Kwok Simon GSGB
 * @date: 2020/6/30 23:16
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 时间复杂度分析
 */
public class TimeComplexity01 {
    public static void main(String[] args) {
        System.out.println(getSum(101));
        System.out.println(getSumFast(101));
    }

    /**
     * 对于输入的n，求出从1加到n的和
     *
     * @param n
     */
    public static int getSum(int n) {
        int sum = 0;  // 执行1次
        for (int i = 1; i <= n; i++) {
            sum += i; // 执行n次
        }
        return sum;   // 执行1次
    }

    public static int getSumFast(int n) {
        return (1 + n) * n / 2; // 执行一次
    }
}
