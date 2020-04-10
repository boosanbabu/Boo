package com.boo.leetcode.contests.weekly.c178;

import com.boo.CommonUtil;
import com.boo.datastructure.list.ListNode;
import com.boo.datastructure.tree.TreeNode;
import com.boo.datastructure.tree.TreeUtil;

public class Question1 {
	public boolean isSubPath(ListNode head, TreeNode root) {
		ListNode ptr = head;
		return isSubPath(ptr, head, root);
	}

	public boolean isSubPath(ListNode head, ListNode hRoot, TreeNode root) {
		if (head == null)
			return true;
		if (root == null)
			return false;
		if (head.val == root.val) {
			head = head.next;
		} else {
			head = hRoot;
		}
		boolean left = isSubPath(head, hRoot, root.left);
		boolean right = isSubPath(head, hRoot, root.right);
		return left || right;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode();
		l1.val = 1;
		ListNode l2 = new ListNode(), l4 = new ListNode(), l6 = new ListNode(), l8 = new ListNode(),
				l10 = new ListNode();
		l1.next = l10;
		l4.next = l2;
		l4.next = l6;
		// l6.next = l8;
		l2.val = 2;
		l4.val = 4;
		l6.val = 6;
		l8.val = 8;

		TreeNode root = TreeUtil.constructTreeFromLevelOrder(1);
		root.right = TreeUtil.constructTreeFromLevelOrder(1, 10, 1, 9);

		Question1 q = new Question1();
		System.out.println(q.isSubPath(l1, root));
	}

}
