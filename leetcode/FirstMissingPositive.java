


public class FirstMissingPositive {
	
	 public int firstMissingPositive(int[] nums) {
		if (nums.length == 0) return 1; //special case
		//nums = (int[])new HashSet<Integer>(Arrays.asList(nums)).toArray(); //remove duplicate
		//int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 1 || nums[i] > nums.length) nums[i] = 0;
			//else count++;
		}
		boolean[] countain = new boolean[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0 && nums[i] <= nums.length) countain[nums[i] - 1] = true;
		}
		for (int i = 0; i < countain.length; i++) {
			if (!countain[i]) return i + 1;
		}
		return nums.length + 1;
		
		
	 }
	 
	 /**
	  * Better way
	  */
	 public int firstMissingPositive2(int[] nums) {
			if (nums == null || nums.length == 0) return 1; //special case
			for (int i = 0; i < nums.length; i++) {
				if(nums[i] < 1 || nums[i] > nums.length || nums[i] == i + 1 || nums[i] == nums[nums[i] - 1]) continue;
				else {
					int temp = nums[nums[i] - 1];
					nums[nums[i] - 1] = nums[i];
					nums[i] = temp;
					i--;
				}
			}
			for (int i = 0; i < nums.length; i++) {
				if (i + 1 != nums[i]) return i + 1;
			}
			return nums.length + 1;
			
		 }
}
