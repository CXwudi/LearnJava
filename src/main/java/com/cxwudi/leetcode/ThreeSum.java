package main.java.com.cxwudi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeSum {
	public List<List<Integer>> DullthreeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> results = new ArrayList<>();
		// Integer[] last = null;
		HashSet<Integer> counted = new HashSet<>();
		// List<Integer> numsList =
		// Arrays.stream(nums).boxed().collect(Collectors.toList());
		// System.out.println(numsList);
		for (int i = 0; i < nums.length - 2; i++) {
			if (counted.add(nums[i])) {
				HashSet<Integer> counted2 = new HashSet<>();
				for (int j = i + 1; j < nums.length; j++) {
					if (counted2.add(nums[j])) {
						// System.out.println(i + " is " + nums[i] +", " + j + " is " + nums[j] +", " +
						// counted2);
						int target = -(nums[i] + nums[j]);
						// HashSet subList = new HashSet(numsList.subList(j+1, numsList.size()));

						if (counted2.contains(target) && (target == nums[j] ? (j == nums.length - 1 ? false : target == nums[j + 1]): true)) {
							// if (counted2.contains(target)) {
							// System.out.println(true);
							Integer[] r = { nums[i], nums[j], target };
							results.add(Arrays.asList(r));
						}
					}
				}
			}
			// System.out.println(counted);

		}
		return results;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> results = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i != 0 && nums[i] == nums[i - 1])
				continue;
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				if (nums[l] + nums[r] == -nums[i]) {
					Integer[] n = { nums[i], nums[l], nums[r] };
					if ((r == nums.length - 1 ? true : nums[r + 1] != nums[r]) && (l == i + 1 ? true : nums[l - 1] != nums[l])) {
						results.add(Arrays.asList(n));
					}
					l++;
					r--;
				} else if (nums[l] + nums[r] < -nums[i]) 
					l++;
				else if (nums[l] + nums[r] > -nums[i]) 
					r--;
			}
		}
		return results;
	}

	public static void main(String[] args) {
		int[] a = {-1,0,1,2,-1,-4};
		List<List<Integer>> aa = new ThreeSum().threeSum(a);
		System.out.println(aa.size());
		for (int i = 0; i < aa.size(); i++) {

			System.out.println(aa.get(i));

		}

	}
}
