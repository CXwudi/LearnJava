package main.java.com.cxwudi.leetcode;

public class InvertBinaryTree {
    
    public TreeNode invertTree(TreeNode root) {
        if (root == null){//should happen once only
            return root;
        }
        if (root.left != null){
            invertTree(root.left);
        }
        if (root.right != null){
            invertTree(root.right);
        }
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
