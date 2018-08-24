

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
	
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		if (pre == null || post == null) {
			return null;
		} else if (pre.length == 0 || post.length == 0) {
			return null;
		}
		for (int i = 0; i < post.length / 2; i++) {
			int temp = post[i];
			post[i] = post[post.length - i - 1];
			post[post.length - i - 1] = temp;
		}
		return recursiveCreateTree(pre, 0, pre.length, post, 0, post.length);
    }
	

	/**create one node base on the a section of pre[] and a section of post[]*/
	private TreeNode recursiveCreateTree(int[] pre, int i, int ie, int[] post, int j, int je) {
		
		if(pre[i] != post[j]) return null;//check the pre and post are legal
		TreeNode root = new TreeNode(pre[i]);
		return null;
	}



	public static void main(String[] args) {

	}

}
