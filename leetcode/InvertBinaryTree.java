
public class InvertBinaryTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
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
