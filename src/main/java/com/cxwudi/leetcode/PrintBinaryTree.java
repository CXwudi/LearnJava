package main.java.com.cxwudi.leetcode;

import java.util.*;

public class PrintBinaryTree {
	
	public int deep (TreeNode root) {
		return root == null ? 0: Math.max(deep(root.left), deep(root.right)) + 1;
		//return root.left == null && root.right == null ? 1: Math.max(deepth(root.left), deepth(root.right)) + 1;
	}
	public void DFS(List<List<String>> list, TreeNode root, int p1, int p2,int d) {//[startindex, endindex)
		if (root != null){
			//ArrayList a = new ArrayList<String>(Arrays.asList(new String[length]));
			list.get(d).set((p1+p2)/2, "" + root.val);
			
			DFS(list, root.left, p1, (p1+p2)/2 , d+1);
			DFS(list, root.right, (p1+p2)/2 +1, p2, d+1);
		}
	}
	public List<List<String>> printTree(TreeNode root) {
		int depth = deep(root), length = 0;
		for (int i = 0; Math.pow(2, i) < Math.pow(2, depth) ; i++) {
			length += Math.pow(2, i);
		}

		List<List<String>> list = new ArrayList<>(depth);
		for (int i = 0; i < depth; i++) {
			list.add(new ArrayList<>(length));
			for (int j = 0; j < length; j++)
				list.get(i).add("");
		}
		
		DFS(list, root, 0, length, 0);
		return list;
    }
	
	public static void main(String[] args) {
		System.out.println("��ӡ����");
	}

}
