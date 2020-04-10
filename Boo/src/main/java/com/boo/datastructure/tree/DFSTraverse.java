package com.boo.datastructure.tree;

public class DFSTraverse {

	public void inorder(TreeNode root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}

	public void preorder(TreeNode root) {
		if (root == null)
			return;
		System.out.print(root.val + " ");
		preorder(root.left);
		preorder(root.right);
	}

	public static void main(String arg[]) {

		TreeNode three = new TreeNode(3);
		TreeNode nine = new TreeNode(9);
		TreeNode twenty = new TreeNode(20);
		TreeNode fifteen = new TreeNode(15);
		TreeNode seven = new TreeNode(7);
		TreeNode six = new TreeNode(6);
		TreeNode four = new TreeNode(4);
		TreeNode two = new TreeNode(2);

		three.left = nine;
		three.right = twenty;
		twenty.left = fifteen;
		fifteen.left = six;
		fifteen.right = seven;
		six.left = four;
		four.right = two;

		DFSTraverse trv = new DFSTraverse();
		System.out.println("Inorder ");
		trv.inorder(three);
		System.out.println("\nPreorder");
		trv.preorder(three);

	}
}
