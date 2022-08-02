package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 两数之和
 *
 * @author zhxy
 * @Date 2021/5/28 4:07 下午
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,7,11,15};
        TwoSum sol = new TwoSum();
        for (int i : sol.twoSum(nums, 9)) {
            System.out.println(i);
        }
    }


    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        System.out.println(nums);
        for (int i = 0; i < nums.length; i++) {
            int temp = target-nums[i];
            if (map.get(temp) != null && i != map.get(temp)) {
                result[0] = map.get(temp);
                result[1] = i;
                return result;
            }
        }
        return result;
    }
}
