package main.java.com.cxwudi.leetcode;



public class MissingNumber {

	public int missingNumber(int[] nums) {
        boolean[] array = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
			array[nums[i]] = true;
		}
        for (int i = 0; i < array.length; i++) {
			if (!array[i]) return i;
		}
        return -1;
    }
	/**
	 * Better Way, using guess equation: n * (n + 1) / 2 to get the total
	 */
	public int missingNumber2(int[] nums) {
        int total = nums.length * (nums.length + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
			total -= nums[i];
		}
        return total;
    }
	public static void main(String[] args) {
		System.out.println(new MissingNumber().missingNumber(new int[]{1,2,3,4,5}));

	}

}
