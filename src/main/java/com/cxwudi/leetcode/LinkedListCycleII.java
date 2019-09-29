package main.java.com.cxwudi.leetcode;

import java.util.HashSet;

public class LinkedListCycleII {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public ListNode detectCycle(ListNode head) {
		if (head == null) return null;
        LinkedListCycleII.ListNode p1 = head, p2 = head;
        //int i1 = 0, i2 = 0;
        do {
        	if (p2.next == null || p2.next.next == null) return null;
        	p1 = p1.next; //i1++;
        	p2 = p2.next.next; //i2+= 2;
        } while (p1 != p2 && p1 != null && p2 != null);
        p1 = head;
        while (p1 != p2) {
        	p1 = p1.next;
        	p2 = p2.next;
        }
        return p1;
    }

	public static void main(String[] args) {
		
	}

}
