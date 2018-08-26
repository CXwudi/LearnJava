package leetcode;

import java.util.Arrays;
import java.util.logging.Logger;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre != null && post != null) {
            if (pre.length != 0 || post.length != 0) {
                //get the reversed post-order. 
                for (int i = 0; i < post.length / 2; i++) {
                    int temp = post[i];
                    post[i] = post[post.length - i - 1];
                    post[post.length - i - 1] = temp;
                }
                Logger.getGlobal().info("pre[] = " + Arrays.toString(pre));
                Logger.getGlobal().info("post[] = " + Arrays.toString(post));
                //reversed post-order is a pre-order that going to right-hand side of treenode at first.
                //together it's easy to create tree using reversed post-order and pre-order.
                return recursiveCreateTree(pre, 0, pre.length, post, 0, post.length);
            }
        }
        return null;

    }

    /** create one node base on the a section of pre[] and a section of post[] */
    private TreeNode recursiveCreateTree(int[] pre, int i, int ie, int[] post, int j, int je) {
        Logger.getGlobal().info(() -> new StringBuilder()
                .append("i = ").append(i).
                append(", ie = ").append(ie).
                append(", j = ").append(j).
                append(", je = ").append(je).
                toString()
                );
        assert pre[i] == post[j] : "the first int of both array is not same";
        assert ie - i == je - j : "sub-arrays length are not same";
      //if no int
        if (ie == i) return null;
        //create root node
        int val = pre[i]; 
        TreeNode node = new TreeNode(val);
        // if only one int in sub-array
        if (ie - i < 2) return node;
        //start creating children node
        int lVal = pre[i + 1], rVal = post[j + 1];
        if (lVal == rVal) {// if only one child
            node.left = recursiveCreateTree(pre, i + 1, ie, post, j + 1, je);
        } else { //two children
            int lValInPost = findValIndexFromAnotherArray(post, j, je, lVal);
            int rValInPre  = i + 1 + je - lValInPost;
            assert lValInPost != -1 && rValInPre != -1 : "invalid arrays to make tree";
            node.left = recursiveCreateTree(pre, i + 1, rValInPre, post, lValInPost, je);
            node.right = recursiveCreateTree(pre, rValInPre, ie, post, j + 1, lValInPost);
        }
        
        return node;
    }

    private int findValIndexFromAnotherArray(int[] subArray, int start, int end, int valToFind) {
        for (int k = start + 1; k < end; k++) {
            if (subArray[k] == valToFind) {
                return k;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode node = new ConstructBinaryTreeFromPreorderAndPostorderTraversal().constructFromPrePost(
                new int[] {1, 2, 4, 8, 9, 5, 10, 3, 6, 11, 12, 7},
                new int[] {8, 9, 4, 10, 5, 2, 11, 12, 6, 7, 3, 1}//this is post
                );
        var list = new BinaryTreePostorderTraversal().postorderTraversalEsayRecursive(node);
        System.out.println(list);
        assert false;
    }

}
