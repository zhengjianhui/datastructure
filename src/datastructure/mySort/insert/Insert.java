package datastructure.mySort.insert;

import datastructure.strategy.Strategy;

/**
 * 插入类排序
 */
public class Insert {

    /**
     * 直接插入排序
     *
     * 核心是比较当前元素和前一位之间的大小
     * 如果当前元素小于前一位，则前一位后移，空出下标
     * 空出后当前继续和空出的前一位开始逆推对比，如果还是小于则后移，直到大于位置结束比较插入空位
     *
     * @param arr      待排序数组
     * @param lo       排序开始的范围(必要条件：lo >= 0)
     * @param hi       排序结束的范围(最大为 arr.length - 1)
     * @param strategy 排序策略
     */
    public static void directlyInsertSort(Object[] arr, int lo, int hi, Strategy strategy) {

        for (int i = lo + 1; i <= hi; i++) {

            if (strategy.compare(arr[i], arr[i - 1]) < 0) {

                // 例如 下标6的元素比较下标5的元素 小于下标5的元素时
                Object temp = arr[i];   // 记录下标6的值
                arr[i] = arr[i - 1];    // 原来5下标的值往后到6的位置 此时5的位置空出来

                int j = i - 2; // i - 1的位置已经比较过了，往前继续推一位（空位前一位）
                // 继续和空位前的元素对比，如果比空位前的元素小，空位前一位移动到空位，前一位空出来，依次类推比较完前的数值
                for (; j >= lo && strategy.compare(temp, arr[j]) < 0; j--) {
                    arr[j + 1] = arr[j];
                }

                // 插入真正的位置
                arr[j + 1] = temp;
            }

        }

    }

    /**
     * temp = 当前需要比较的变量
     * low =  最小边界
     * higt = 当前需要比较的变量的前一位（最大边界， 也就是用当前元素比较前一位到0角标的所有元素）
     *
     * 折半查找负责减少 最大边界 （最大边界初始为 当前元素前一位，边界为 i - 1， i = lo + 1 当前元素下标i）
     * 折半如果higt 最大边界值 小于 j 则说明 higt 到 j 直间的元素应该后移一位
     * higt + 1 的值会是实际插入位置的下标
     *
     * 本质上 这般后 higt 会是插入位置的最小边界
     * higt - 1 等于最小边界前一位
     * 最小边界到 j 之间的元素后移一位
     * 最最小边界就空出来，空出来的地方就是插入位置
     *
     * 本质上第一次比较lo为1(i + 1)， hi = i-1（0）所以和插入排序一样是从最小边界开始比较
     * 所以每次比较时 lo 前的元素都是有序的，这时候用折半查找找到 插入位置
     * 将 lo（i + 1）前的元素到插入位置元素都后移以为，空出插入位置时，插入元素
     *
     * @param arr      待排序数组
     * @param lo       排序开始的范围(必要条件：lo >= 0)
     * @param hi       排序结束的范围(最大为 arr.length - 1)
     * @param strategy 排序策略
     */
    public static void binInsertSort(Object[] arr, int lo, int hi, Strategy strategy) {

        for (int i = lo + 1; i <= hi; i++) {

            Object temp = arr[i]; // 保存带插入元素
            int higt = i - 1; // 最大边界 （最大边界初始为 当前元素前一位， i = lo + 1 当前元素下标i， 边界为 i - 1）
            int low = lo; // 设置初始期间(第一次与0比，之后最大边界 随 i 递增， lo每次循环不变)

            // 折半查找负责减少 最大边界
            while (low <= higt) {
                int mid = (low + higt) / 2;
                // 如果比 mid 小说明往左 最大边界为 higt = mid - 1
                if (strategy.compare(temp, arr[mid]) < 0) {
                    higt = mid - 1;

                    // 如果大于等于 说明在右边
                } else {
                    low = mid + 1;
                }
            } // 出循环时，找到插入位置

            // 如果 higt 有值 且小于 j 说明找到元素 并且为插入的最大边界
            // j = i - 1等于当前元素的前一位
            // arr[higt + 1] = temp; 将前一位移动到当前位置上
            // 将 j 到 higt 之间的元素全部后移一位
            for (int j = i - 1; j > higt; j--) {
                arr[j + 1] = arr[j];
            }

            // 插入真正的排序
            arr[higt + 1] = temp;
        }

    }


    /**
     * 希尔排序的步长值互质（都不能被对方整除），并且最后一个步长值为1（等于一次直接插入排序）
     *
     * @param arr           带排序数组
     * @param lo            数组最小边界
     * @param hi            数组最大边界
     * @param strategy      排序策略
     * @param delta         随机步长存放的数组
     */
    public static void shellSort(Object[] arr, int lo, int hi, Strategy strategy, int[] delta) {

        for (int i = 0; i < delta.length; i++) {
            shellInsert(arr, lo, hi, strategy, delta[i]);
        }
    }


    /**
     *
     * @param arr               待排序数组
     * @param lo                数组最小边界
     * @param hi                数组最大边界
     * @param strategy          排序策略
     * @param delta             随机步长
     */
    public static void shellInsert(Object[] arr, int lo, int hi, Strategy strategy, int delta) {

        // 假设 lo = 0 delta 等于5 那么temp 就是 下标为5的元素
        // lo + delta 不能大于边界 i < hi 就是边界检查（防止数组下标越界）
        for (int i = lo + delta; i < hi; i++) {

            if(strategy.compare(arr[i], arr[i - delta]) < 0) { // 此时如果 下标5的元素小于 0 的元素

                Object temp = arr[i];
                int j = i - delta;

                //  j 每次都往前推 delta 的步长，并和该步长的元素比较大小，如果小于则交换（大于时退出循环）
                for (; j >= lo && strategy.compare(temp, arr[j]) < 0; j -= delta) {
                    arr[j + delta] = arr[j];
                }

                arr[j + delta] = temp;
            }
        }
    }

}
