package com.cxwudi.leetcode;

import java.util.ArrayList;
import java.util.HashSet;

public class PerfectSquares {
    public int numSquares(int n) {
        if (n <= 0)
            return 0;
        ArrayList<Integer> squareArray = new ArrayList<>();
        for (int i = 1; i*i <= n; i++)
            squareArray.add(i*i);

        HashSet<Integer> bfsNode = new HashSet<>();
        bfsNode.add(n);
        int count = 0;
        while (!bfsNode.isEmpty()){
            count++;
            HashSet<Integer> tempbfsNode = new HashSet<>();
            for (Integer squareNumber: squareArray) {
                for (Integer node: bfsNode){
                    tempbfsNode.add(node - squareNumber);
                    if (tempbfsNode.contains(0))
                        return count;
                }
            }
            bfsNode = new HashSet<>(tempbfsNode);

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(12));
        System.out.println(new PerfectSquares().numSquares(16));
        System.out.println(new PerfectSquares().numSquares(23));
        System.out.println(new PerfectSquares().numSquares(3));
    }
}
