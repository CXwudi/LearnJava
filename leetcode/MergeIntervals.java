
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
	
	  
	public class Interval {
		int start;
		int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}
	 
	public List<Interval> merge(List<Interval> d) {//By CXwudi
		if(d == null || d.size() == 0) return d;
		int l = 0;
        for (Interval interval : d) {
			l = Math.max(l, interval.end);
		}
        int[] buffer = new int[l + 1];
        for (int i = 0; i < buffer.length; i++) {
			buffer[i] = 0;
		}
        //Integer.MAX_VALUE is a special mark for interval that the start = end.
        for (int i = 0; i < d.size(); i++) {
			buffer[d.get(i).start] = buffer[d.get(i).start] == Integer.MAX_VALUE? 1: buffer[d.get(i).start] + 1;
			buffer[d.get(i).end] = buffer[d.get(i).end] == Integer.MAX_VALUE? -1: buffer[d.get(i).end] - 1;
			if (d.get(i).start == d.get(i).end && buffer[d.get(i).start] == 0) {
				buffer[d.get(i).start] = Integer.MAX_VALUE;
			}
		}
        System.out.println(Arrays.toString(buffer));
        int i1 = 0, i = 0; // two pointers
        int adder = 0;
        ArrayList<Interval> merged = new ArrayList<>();
        for (i = 0; i < buffer.length; i++) {
			if (buffer[i] == Integer.MAX_VALUE) {
				if (adder == 0) merged.add(new Interval(i, i));
				continue;
			}
			if(buffer[i] > 0) {
				if(adder == 0) i1 = i;
			}
			adder += buffer[i];
			if (buffer[i] < 0) {
				if(adder == 0) {
					merged.add(new Interval(i1, i));
				}
			}
		}
        return merged;
    }

}
