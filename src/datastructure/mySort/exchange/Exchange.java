package datastructure.mySort.exchange;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/13.
 */
public class Exchange {

    /**
     * 冒泡排序 会将最大的往后推
     * 外层每次循环完一次，就有一个最大值在最后，这时内层的最大边界减去外层循环次数（相当于减去已经倒叙去人的外层循环数n个最大数）
     *
     * @param arr
     * @param strategy
     */
    public static void bubbleSort(Object[] arr, Strategy strategy) {

        for (int i = 0; i < arr.length - 1 ; i++) { // 每次循环减少一个元素（i每循环一次，就会将最大推倒最后）

            // 每次从0 开始比较所有元素（每次比较最大边界减去 i 的值，省去已经确定的大值之间的比较）
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if(strategy.compare(arr[j], arr[j + 1]) > 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }
    }
//
//        for (int i = 0; i < arr.length - 1 ; i++) {  // 最后一个元素没必要比较
//
//            for (int j = i + 1; j < arr.length ; j++) {    // 由于i从0 开始 所以 j 从i 后一位开始和i比较
//
//                if(strategy.compare(arr[i], arr[j]) > 0) { // 大于后续元素
//                    Object temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
//
//                }
//            }
//
//        }

    /**
     * 划分算法
     * 本质是选一个 pivot
     * 把比pivot 小的划分到左边
     * 把比pivot 大的划分到右边
     * 是用两个指针lo 和 hi分别指向 序列的取值范围
     * 取lo 所指元素为枢 既pivot = arr[lo]（一开始将 lo 设置为 pivot 除了中枢外，pivot 还起着追尾交换中的temp的作用 此时lo可以视为null）
     *
     * 划分首先
     * 从hi所指的位置起向前逐一搜索到第一个比pivot小的元素，并将其设置到lo所指的位置 并且每次循环hi--到前一个位置上
     * 然后从lo所指的位置逐一向后搜索到第一个比pivot大的元素设置到hi的位置 并且每次循环hi ++到后面一个位置上
     *
     * 不断重复以上两部，直到lo = hi 最后将pivot 设置到 lo 和 hi 共同指向的位置
     *
     * @param arr           带排序数组
     * @param lo            最小边界
     * @param hi            最大边界
     * @param strategy      排序策略
     * @return              中枢元素
     */
    private static Object pivot(Object[] arr, int lo, int hi, Strategy strategy) {

        // 设置枢 一开始将 lo 设置为 pivot 除了中枢外，pivot 还起着追尾交换中的temp的作用
        Object pivot = arr[lo];

        // 由于每次对比后交换的位置要不比 pivot 大要不比 pivot 小
        // 所以每次找到新的值时，都不会出现在之前的位置找到的值的位置
        // 当lo = hi 时，左右序列划分完毕
        while(lo < hi) {

            // 每次循环 找到值后 lo 与 hi 会暂时停留在找到位置上，这个位置不会和之前找到交换后的位置重叠
            // 从hi开始逐一往前 找到第一个比 pivot 小的元素，设置到lo的下标上
            while(lo < hi && strategy.compare(arr[hi], pivot) >= 0) { // 找到比 pivot 小的值出循环
                hi --;
            }

            // 将找到后的值设置到 lo 下标位置上，此时 hi 的下标值与lo下标值 重复，可以当成null 来使用（之后lo的找到后直接插入）
            arr[lo] = arr[hi];

            // 从lo开始逐一往前 找到第一个比 pivot 小的元素，设置到lo的下标上
            while(lo < hi && strategy.compare(arr[lo], pivot) <= 0) { // 找到比 pivot 大的值出循环
                lo ++;
            }

            // 将找到后的值设置到 hi 下标位置上，此时 lo 的下标值与hi下标值 重复，可以当成null 来使用（之后hi的找到后直接插入）
            arr[lo] = arr[hi];

        }

        // 设置中枢点 此时lo = hi
        arr[lo] = pivot;

        return pivot;
    }


    /**
     * 快速排序
     * @param arr       待排序序列
     * @param lo        序列最小边界
     * @param hi        序列最大边界
     * @param strategy  排序策略
     */
    public static void quickSort(Object[] arr, int lo, int hi, Strategy strategy) {
        int pivot = (int) pivot(arr, lo, hi, strategy);

        // +1 -1 避免改动到中枢
        // 左边序列继续划分算法
        quickSort(arr, lo, pivot - 1, strategy);
        // 右边序列继续划分算法
        quickSort(arr, pivot + 1, hi, strategy);

    }
}
