package datastructure.mySort.select;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/15.
 *
 * 堆排序
 * 堆的定义，任何一非叶节点的关键字不大于或者不小于其左右孩子节点的关键字。
 * 0 角标不存放元素
 */
public class HeapSort {


    /**
     * 堆排序
     * @param arr
     * @param strategy
     */
    public static void heapSort(Object[]arr, Strategy strategy) {
        // 获取数组边界
        int n = arr.length - 1;

        // 初始化建立堆
        // 二叉树的结构决定了，叶子节点 - 1 会等于所有祖先节点之和，所以数组边界 / 2 之前的节点可以看做非叶子节点(排序后)
        // 堆的定义，任何一非叶节点的关键字不大于或者不小于其左右孩子节点的关键字。
        // 建堆时，n / 2可以看做最后一个非叶子节点（通过adjust 对比他的两个子节点 并且排序）
        for (int i = n / 2; i >= 1 ; i--) {
            adjustHeap(arr, i, n, strategy);
        }

        for (int i = n; i > 1; i--) {
            // 堆顶元素， 堆顶从1开始
            Object temp = arr[1];
            // 交换堆顶与堆底的元素
            arr[1] = arr[i];
            arr[i] = temp;

            // 调整堆  // i - 1防止之后 arr[i] 与 arr[i + 1] 比较是溢出
            adjustHeap(arr, 1, i - 1, strategy);
        }


    }


    /**
     * 调整堆
     *
     * @param arr
     * @param lo            lo 的最下边界 1 最大边界 hi / 2
     * @param hi            hi = arr.length - 1
     * @param strategy
     */
    private static void adjustHeap(Object[] arr, int lo, int hi, Strategy strategy) {

        Object temp = arr[lo];

        // 第一个叶子节点会是 lo * 2 第二个叶子节点会是 lo * 2 + 1
        for (int i = 2 * lo; i <= hi ; i = i * 2) { // 如果i * 2 是非叶子节点就会循环选择下去

            // i < hi 防止下标越界
            // 指向值较大的下标
            // （例如lo=0 时，i = 0, 此时比较 arr[i] 与 arr[i + 1] 如果i < i + 1 则让i指向 i + 1 的位置）
            // 叶子节点间先比较出最大的
            if(i < hi && strategy.compare(arr[i], arr[i + 1]) < 0) {
                i ++;
            }

            // 如果temp 大于等于两个子元素 则结束循环
            if(strategy.compare(temp, arr[i]) >= 0) {
                break;
            }

            // 如果arr[lo] 的元素小于最大的元素，则让arr[lo]指向当前最大的元素
            // 父节点与叶子节点中最大的比较， 如果小于则交换位置
            arr[lo] = arr[i];
            // 此时lo 指向i（lo 指向 i 的位置方便之后插入 temp ）
            lo = i;

        }

        arr[lo] = temp;
    }
}
