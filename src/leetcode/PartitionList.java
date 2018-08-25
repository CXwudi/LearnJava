package leetcode;

/*
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	
	For example,
	Given 1->4->3->2->5->2 and x = 3,
	return 1->2->2->4->3->5.
	
	You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode partition(ListNode head, int x) {
		if (head == null) {
			return null;
		}
		ListNode virtualHead = new ListNode(Integer.MIN_VALUE), pointer1 = virtualHead, pointer2 = virtualHead, iterator = virtualHead;// p1 < x; p2 >= x
		virtualHead.next = head;
		while (iterator != null) {

			// pointer manager
			if (iterator.val < x) {
				pointer1 = iterator;
			} else {
				pointer2 = iterator;
			}
			// moving process
			if (iterator.next != null ? (pointer1.val < x && pointer1.next.val >= x && pointer2.val >= x && pointer2.next.val < x) : false) {
				//System.out.println("changed p1 =" + pointer1.val + ",p1.next = " + pointer1.next.val + ", p2 = " + pointer2.val + ", p2.next = " + pointer2.next.val);
				ListNode temp1 = pointer1.next;
				pointer1.next = pointer2.next;
				pointer2.next = pointer2.next.next;
				pointer1.next.next = temp1;
				pointer1 = pointer1.next;
			} else {
				iterator = iterator.next;
			}
		}
		return virtualHead.next;
	}

	public ListNode createLinkedList(int[] numbers, int index) {
		ListNode head = null;
		if (index < numbers.length) {
			head = new ListNode(numbers[index]);
			head.next = createLinkedList(numbers, index+1);
		}
		return head;
	}

	public void printPartitionList(int[] a, int b) {
		
		ListNode head = createLinkedList(a, 0), Iterator = head;
		while (Iterator != null) {
			System.out.print(Iterator.val + ", ");
			Iterator = Iterator.next;
		}
		System.out.println("after process");
		Iterator = partition(head, b);
		while (Iterator != null) {
			System.out.print(Iterator.val + ", ");
			Iterator = Iterator.next;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3, -1, 0, 4 };
		int b = 2;
		new PartitionList().printPartitionList(a,b);
	}

}
