package leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * This is not a question in leetcode.
 * This is just a util for printing BST from leetcode in
 * pre-order, in-order, and post-order
 * @author CXwudi
 *
 */
public class BSTtraversalTool {
    public enum Mode {PRE, IN, POST};//Learn Java: in-class enum are static by default
    public static List<Integer> traversalPreorder(TreeNode root){
        return traversalIn(root, Mode.PRE);
    }
    
    public static List<Integer> traversalInorder(TreeNode root){
        return traversalIn(root, Mode.PRE);
    }
    
    public static List<Integer> traversalPostorder(TreeNode root){
        return traversalIn(root, Mode.PRE);
    }
    
    public static List<Integer> traversalIn(TreeNode root, Mode m) {
        List<Integer> treeList = new ArrayList<Integer>();
        helper(treeList, root, m);
        return treeList;
    }

    private static void helper(List<Integer> list, TreeNode node, Mode m) {
        if (node == null) return;
        if (m == Mode.PRE) list.add(node.val);
        helper(list, node.left, m);
        if (m == Mode.IN) list.add(node.val);
        helper(list, node.right, m);
        if (m == Mode.POST) list.add(node.val);
    }
}
