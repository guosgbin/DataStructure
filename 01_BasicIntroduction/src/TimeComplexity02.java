import java.util.Arrays;

/**
 * @author: Kwok Simon GSGB
 * @date: 2020/7/2 8:05
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 时间复杂度分析
 */
public class TimeComplexity02 {
    public static void main(String[] args) {
        int[] arr1 = {1, 4, 2, 7, 5};
        int[] arr = {5,4,3,2,1};
//        selectSort(arr);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 初始化最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    // 假如位置i的值大于位置j的值,则将minIndex复制为j
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // i位置的值不是最小值,则调换
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        boolean isExchange = true; // 是否发生交换操作的标志
        int count =0;
        for (int i = 0; i < arr.length && isExchange; i++) {
            // 假如没有发生交换操作，则下面循环操作完毕后还是false
            // 是false则代表数组已经是排序好的。
            isExchange = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 发生了交换，标志置true
                    isExchange = true;
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
