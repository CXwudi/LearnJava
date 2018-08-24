

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;



public class BinaryTreePostorderTraversal {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> treeList = new LinkedList<>();
		Deque<TreeNode> nodesStack = new LinkedList<>();
		if(root != null) nodesStack.push(root);
		while (!nodesStack.isEmpty()) {
			TreeNode curNode = nodesStack.pop();
			treeList.add(0, curNode.val);
			if (curNode.left != null) nodesStack.push(curNode.left);
			if (curNode.right != null) nodesStack.push(curNode.right);
		}
		return treeList;
	}
	

	public List<Integer> postorderTraversalEsayRecursive(TreeNode root) {
		List<Integer> treeList = new ArrayList<Integer>();
        helper(treeList, root);
        return treeList;
    }
	private void helper(List<Integer> list, TreeNode node) {
		if(node == null) return;
		helper(list, node.left);
		helper(list, node.right);
		list.add(node.val);
		
	}
	public static void main(String[] args) {

	}

}
