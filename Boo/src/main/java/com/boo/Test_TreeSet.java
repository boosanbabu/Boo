package com.boo;

import java.util.HashSet;
import java.util.TreeSet;

public class Test_TreeSet {

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int minDistFromSource;

		@Override
		public int compareTo(Node o) {
			return this.minDistFromSource - o.minDistFromSource;
		}

		@Override
		public String toString() {
			return "Node[x=" + x + ",y=" + y + "]";
		}

	}

	public static void main(String[] args) {

		TreeSet<Node> un_visited = new TreeSet();
		HashSet<Node> hash_set = new HashSet();

		Node a = new Node();
		a.x = 0;
		a.y = 0;
		un_visited.add(a);
		hash_set.add(a);
		System.out.println("TreeSet=" + un_visited);
		System.out.println("HashSet=" + hash_set);

		Node b = new Node();
		b.x = 0;
		b.y = 1;
		un_visited.add(b);
		hash_set.add(b);
		System.out.println("TreeSet=" + un_visited);
		System.out.println("HashSet=" + hash_set);

		Node c = new Node();
		c.x = 0;
		c.y = 2;
		un_visited.add(c);
		hash_set.add(c);
		System.out.println("TreeSet=" + un_visited);
		System.out.println("HashSet=" + hash_set);

	}

}