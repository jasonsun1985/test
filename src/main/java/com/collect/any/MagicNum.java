package com.collect.any;

/**
 * 卡普耶卡变换，一个任意的四位正整数（全相同的除外，如1111）。
 * 将数字重新组合成一个最大的数和最小的数相减，重复这个过程，最多七步，必得6174。
 * <br>
 *  
 * <p>
 * Create on : 2017年5月8日<br>
 * <p>
 * </p>
 * <br>
 * @author 孙磊<br>
 * @version testTec v6.2.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class MagicNum {

    public static void main(String[] args) {
        int data = 8158;
        int result = 0;
        int[] sortData = new int[4];
        System.out.println("原始数据 ： " + data);
        while (result != 6174) {
            sortData = getArrayData(data);
            result = bubbleSort(sortData);
            System.out.println(result);
            data = result;
        }
    }

    private static int bubbleSort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[i] < unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }

            }
        }
        StringBuffer tempMax = new StringBuffer();
        for (int k = 0; k < unsorted.length; k++) {
            tempMax.append(unsorted[k]);
        }
        int max = Integer.parseInt(tempMax.toString());
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
        StringBuffer tempMin = new StringBuffer();
        for (int l = 0; l < unsorted.length; l++) {
            tempMin.append(unsorted[l]);
        }
        int min = Integer.parseInt(tempMin.toString());
        System.out.println("  " + max + " - " + min + " = " + (max - min));
        return max - min;
    }

    private static int[] getArrayData(int data) {
        int[] tempData = new int[4];
        int size = String.valueOf(data).length();
        for (int i = 0; i < size; i++) {
            tempData[i] = Integer.parseInt(String.valueOf(data).substring(i, i + 1));
        }
        return tempData;
    }

}
