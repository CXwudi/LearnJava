package leetcode;
import java.util.Arrays;
import java.util.logging.Logger;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre != null && post != null) {
            if (pre.length != 0 || post.length != 0) {
                for (int i = 0; i < post.length / 2; i++) {
                    int temp = post[i];
                    post[i] = post[post.length - i - 1];
                    post[post.length - i - 1] = temp;
                }
                Logger.getGlobal().info("pre[] = {0}" + Arrays.toString(pre));
                Logger.getGlobal().info("post[] = {0}" + Arrays.toString(post));
                return recursiveCreateTree(pre, 0, pre.length, post, 0, post.length);
            }
        }
        return null;

    }

    /** create one node base on the a section of pre[] and a section of post[] */
    private TreeNode recursiveCreateTree(int[] pre, int i, int ie, int[] post, int j, int je) {
        assert pre[i] == post[j] :  "the first int of both array is not same";
        assert ie - i == je - j : "arrays length are not same";
        
        TreeNode root = new TreeNode(pre[i]);
        return null;
    }

    public static void main(String[] args) {
        new ConstructBinaryTreeFromPreorderAndPostorderTraversal().constructFromPrePost(new int[] {6,2,3,1,4,5}, new int[] {5,1,2,3,6,1});
    }

}
