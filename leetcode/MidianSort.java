
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CX无敌 on 2017-06-30.
 */
public class MidianSort {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int temp1 = -999, temp2 = -999, i = 0,j = 0;
        while(i < nums1.length || j < nums2.length){
            temp2 = temp1 + 0;
            if (i == nums1.length || j == nums2.length){
                if(i == nums1.length){
                    temp1 = nums2[j++];
                } else {
                    temp1 = nums1[i++];
                }
            }

            else if (nums1[i] < nums2[j]){
                temp1 = nums1[i++];
            }else{
                temp1 = nums2[j++];
            }

            if (i + j >  Math.ceil((nums1.length + nums2.length)/2.0) && (nums1.length + nums2.length)%2 ==0){
                return (temp1 + temp2)/2.0;
            } else if (i + j >= Math.ceil((nums1.length + nums2.length)/2.0) && (nums1.length + nums2.length)%2 ==1 ){
                return temp1;
            }
        }
        return -1;
    }
    public double findMedianSortedArraysByBinarySearch(int[] nums1, int[] nums2){
        List<Integer> a1 = new ArrayList<>(), a2 = new ArrayList<>();
        a1 = Arrays.stream( nums1 ).boxed().collect( Collectors.toList());
        a2 = Arrays.stream( nums2 ).boxed().collect( Collectors.toList());
        return FckingRecrusiveBinarySearchForFindingMedian((ArrayList)a1,(ArrayList)a2);
        //change int[] to ArrayList<Integer>, and call recursion function.
    }
    public double FckingRecrusiveBinarySearchForFindingMedian(ArrayList<Integer> a1, ArrayList<Integer> a2){
        int s1 = a1.size(), s2 = a2.size();
        if (s1 > s2)
            FckingRecrusiveBinarySearchForFindingMedian(a2,a1);
        //调换a1，a2的位置,让短的变成a1
        System.out.println(a1 + " " + a2);
        //Core of Binary Search
        if ((s1 >=2 && s2 >2) || (s1 >2 && s2 >= 2)){
            if ((a1.get(a1.size()/2) + a1.get(a1.size()/2-1))/2.0 >
                    (a2.get(a2.size()/2) + a2.get(a2.size()/2-1))/2.0){
                //如果a1的中位数比a2的中位数大，取a1的左半边和a2的右半边，继续
                return FckingRecrusiveBinarySearchForFindingMedian(new ArrayList<Integer> (a1.subList(0,(int)Math.round(a1.size()/2.0) )), new ArrayList<Integer> (a2.subList(a2.size()/2,a2.size())));
            } else {//反之，取a2的左半边和a1的右半边，继续
                return FckingRecrusiveBinarySearchForFindingMedian(new ArrayList<Integer> (a1.subList(a1.size()/2,a1.size())), new ArrayList<Integer> (a2.subList(0,(int)Math.round(a2.size()/2.0) )));
            }

        } else if (s1 == 1 && s2 == 1)//base case之一，当两个arraylist都只有一个element。
            return ( a1.get(0) + a2.get(0) ) / 2.0;
        else if (s1 == 2 && s2 == 2){

            if (a1.get(0) < a2.get(0) && a1.get(1) > a2.get(1))
                return (a2.get(0) + a2.get(1))/2.0;
            else if(a1.get(0) > a2.get(0) && a1.get(1) < a2.get(1))
                return (a1.get(0) + a1.get(1))/2.0;
            else{
                if ((a1.get(a1.size()/2) + a1.get(a1.size()/2-1))/2.0 >
                        (a2.get(a2.size()/2) + a2.get(a2.size()/2-1))/2.0){//如果a1的中位数比a2的中位数大，取a1的左半边和a2的右半边，继续
                    return FckingRecrusiveBinarySearchForFindingMedian(new ArrayList<Integer> (a1.subList(0,(int)Math.round(a1.size()/2.0) )), new ArrayList<Integer> (a2.subList(a2.size()/2,a2.size())));
                } else {//反之，取a2的左半边和a1的右半边，继续
                    return FckingRecrusiveBinarySearchForFindingMedian(new ArrayList<Integer> (a1.subList(a1.size()/2,a1.size())), new ArrayList<Integer> (a2.subList(0,(int)Math.round(a2.size()/2.0) )));
                }
            }

        } else if (s1 == 1 ){
            if (s2 % 2 == 1){
                //double midian2 = (a2.get(a2.size()/2) + a2.get(a2.size()/2-1))/2.0;
                double midian2 = a2.get(a2.size()/2);
                if (a1.get(0)<= midian2){
                    if (a1.get(0) < a2.get(a2.size()/2-1) )
                        return (midian2 + a2.get(a2.size()/2-1))/2.0;
                    else
                        return (midian2 + a1.get(0))/2.0;
                } else {
                    if (a1.get(0) > a2.get(a2.size()/2+1) )
                        return (midian2 + a2.get(a2.size()/2+1))/2.0;
                    else
                        return (midian2 + a1.get(0))/2.0;
                }
            } else {

            }
        }
        return -1;
    }
    public double findMedianSortedArrays2(int A[], int B[]) {
        int n = A.length;
        int m = B.length;
        // the following call is to make sure len(A) <= len(B).
        // yes, it calls itself, but at most once, shouldn't be
        // consider a recursive solution
        if (n > m)
            return findMedianSortedArrays2(B, A);

        // now, do binary search
        int k = (n + m - 1) / 2;
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        while (l < r) {
            int midA = (l + r) / 2;
            int midB = k - midA;
            if (A[midA] < B[midB])
                l = midA + 1;
            else
                r = midA;
        }

        // after binary search, we almost get the median because it must be between
        // these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]

        // if (n+m) is odd, the median is the larger one between A[l-1] and B[k-l].
        // and there are some corner cases we need to take care of.
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if (((n + m) & 1) == 1)
            return (double) a;

        // if (n+m) is even, the median can be calculated by
        //      median = (max(A[l-1], B[k-l]) + min(A[l], B[k-l+1]) / 2.0
        // also, there are some corner cases to take care of.
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0;
    }

}
