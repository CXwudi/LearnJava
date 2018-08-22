
import java.util.HashMap;

public class TrapRainWater {
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        int highest = 0, water = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(height[0],0); map.put(height[1],1);
        highest = Math.max(height[0],height[1]);
        for (int i = 2; i < height.length; i++) {
            Integer lastIndex = 0;

            if (height[i] > 0)
            for (int j = highest; j > 0 ; j--) {

                if (map.get(j) != null){
                    if (map.get(j) >= lastIndex){
                        lastIndex = map.get(j);
                    }

                }
                //System.out.println("at i = " + i + ", check height " + j + "cm at index " + lastIndex);
                if (j <= height[i]){
                    water += i - lastIndex - 1;
                }
                //System.out.println(water);
                if (lastIndex == i-1)
                    break;
            }

            highest = Math.max(highest, height[i]);
            map.put(height[i],i);

        }
        return water;
    }

    public static void main(String[] args) {
        //System.out.println(new TrapRainWater().trap(array));
    }
}
