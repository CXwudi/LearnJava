package main.java.com.cxwudi.leetcode;

import java.util.HashMap;
import java.util.Objects;

public class LeastOpsExpressTarget {
	HashMap<Integer, Integer> nowToTargetMap = new HashMap<>();
	public int leastOpsExpressTarget(int x, int target) {
        return leastOpsExpressTargetHelper(x, target, 0) - 1;//-1 here to get rid of the first +-1, because all plaza is counted as +- ..... +-...... +-......
    }

	private int leastOpsExpressTargetHelper(int x, int target,  int now) {
		int gap = Math.abs(target - now);
		//System.out.println("\nround: now = " + now + ", targer = " + target + ", x = " + x);
		if (gap == 0) {
			//System.out.println("gap = 0, done");
			return 0;
		}
		else if (gap == x) {
			//System.out.println("gap = x, +1 <= +-x");
			return 1;
		}
		else if (nowToTargetMap.containsKey(gap)) {
			//System.out.println("lookup table has the record, when gap = " + gap + ", the value should be "+ nowToTargetMap.get(gap));
			return nowToTargetMap.get(gap);
		}
		int thisCount = 0;
		if (gap < x) { //handle +x/x +x/x ...
			int count1 = gap * 2;
			
			int newNow = now + (target - now > 0 ? x:-x);
			int newGap = Math.abs(target - newNow);
			int count2 = 1 + newGap * 2; //if +x -x/x -x/x is closer
			
			thisCount = Math.min(count1, count2);
			//System.out.println("gap < x, then thisCount = " + thisCount + ", as coun1 = " + count1 + ", count2 = " + count2);
		} else if (gap > x) { //to handle x*x*x*x...
			int k = (int) (Math.log(gap) / Math.log(x));
			int newNow = (int) (now + (target - now > 0 ? Math.pow(x, k) : -Math.pow(x, k)));
			int newNow2 = (int) (now + (target - now > 0 ? Math.pow(x, k+1) : -Math.pow(x, k+1)));
			int count1 = Integer.MAX_VALUE, count2 = Integer.MAX_VALUE;
			//System.out.println("gap > x, recursion start with k = " + k + " in x^k");
			if (gap > Math.abs(target - newNow)) { // newNow < target
				count1 = k + leastOpsExpressTargetHelper(x, target, newNow);
			}
			if (gap > Math.abs(target - newNow2)) { // newNow2 > target
				count2 = k + leastOpsExpressTargetHelper(x, target, newNow2) + 1; //+1 means one more multiply *
			}
			thisCount = Math.min(count1, count2);
			//System.out.println("recursion done with now = " + now + ", targer = " + target + ", thisCount = " + thisCount + ", as coun1 = " + count1 + ", count2 = " + count2);
		} else {
			//System.err.println("this shouldn't happen");
		}
		//if (!nowToTargetMap.containsKey(gap) || nowToTargetMap.get(gap) < thisCount) {
		nowToTargetMap.put(gap, thisCount);
		//}
			
		return thisCount;
	}

	public static void main(String[] args) {
//		System.out.println(new LeastOpsExpressTarget().leastOpsExpressTarget(3, 19));
//		System.out.println(new LeastOpsExpressTarget().leastOpsExpressTarget(5, 501));
//		System.out.println(new LeastOpsExpressTarget().leastOpsExpressTarget(100, 100000000));
//		System.out.println(new LeastOpsExpressTarget().leastOpsExpressTarget(3, 365));
		System.out.println(new LeastOpsExpressTarget().leastOpsExpressTarget(2, 125046));
}

}
