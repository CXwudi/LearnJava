package main.java.com.cxwudi.leetcode;

public class RemoveNthNodeFromEndOfList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        else if(head.next == null && n == 1) return null;
        else if (-1 == helper(head, n,0)) {
                return head.next;
        }
        return head;
    }
    public int helper (ListNode node, int n, int countPlus){
        if (node == null)
            return 1;
        else {
            int h = helper(node.next, n,countPlus + 1);
            if (n == h - 1){
                node.next = node.next.next;
            } else if (countPlus == 0 && n == h){
                return -1;
            }

            return h +1;
        }
    }
    public ListNode removeNthFromEndSmartWay(ListNode head, int n) {
        ListNode front = head, rear = head;
        while(n-- > 0)
            rear = rear.next;
        if(rear == null) return head.next;
        while(rear.next != null){
            front = front.next;
            rear = rear.next;
        }
        front.next = front.next.next;
        return head;
    }
}
