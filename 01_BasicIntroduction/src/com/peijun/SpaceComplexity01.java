package com.peijun;

/**
 * @author: Kwok Simon GSGB
 * @date: 2020/7/2 22:21
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 空间复杂度分析
 */
public class SpaceComplexity01 {
    public static void main(String[] args) {
        System.out.println(getFactorial2(10));
    }

    // 交换
    public static void exchange(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    // 递归计算n的阶乘
    public static long getFactorial(int n) {
        if (n < 0) {
            return -1; // 返回-1表示输入错误
        }
        if (n == 0 || n == 1) {
            return 1; // 0的阶乘是1啊，别忘记了，哈哈
        }
        return n * getFactorial(n - 1);
    }

    // 迭代计算n的阶乘。
    public static long getFactorial2(int n){
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
