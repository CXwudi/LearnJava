package com.cxwudi.leetcode;

import java.util.HashMap;

/**
 * @author CX无敌
 *
 */
public class TwoSum {

	/**
	 * @param args
	 */
	public int[] twoSum(int[] nums, int target) {
		//ArrayList<Integer> numsList= (ArrayList<Integer>) Arrays.stream(nums).boxed().collect(Collectors.toList());
		HashMap maps = new HashMap<Integer,Integer>();
		for (int i = 0; i < nums.length; i++) {
			
			if(maps.containsKey(target - nums[i])) {
				int[] a = new int[2];
                a[0] = i;
                a[1] = (int) maps.get(target - nums[i]);
                return a;
			}
			maps.put(nums[i], i);
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
