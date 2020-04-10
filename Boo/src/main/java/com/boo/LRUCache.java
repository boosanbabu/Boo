package com.boo;

import java.util.*;

class LRUCache {

	class Node {
		Node prev, next;
		int key, val;

		Node(int k, int v) {
			key = k;
			val = v;
		}

		public String toString() {
			return this.key + "";
		}
	}

	Map<Integer, Node> map;
	int cap;
	Node head, tail;

	public LRUCache(int capacity) {
		cap = capacity;
		map = new HashMap<>();
		head = new Node(0, 0); // Dummy
		tail = new Node(0, 0); // Dummy
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!map.containsKey(key))
			return -1;
		Node resNode = map.get(key);
		removeNode(resNode);
		insertToHead(resNode);
		return resNode.val;
	}

	void printFullLinkedList() {
		Node ptr = head;
		System.out.println("\nfull list");
		StringBuilder sb = new StringBuilder();

		while (ptr != null) {
			sb.append("[");
			sb.append((ptr.prev != null ? ptr.prev.key : "-") + "," + ptr.key + ","
					+ (ptr.next != null ? ptr.next.key : "-"));
			sb.append("]->");
			ptr = ptr.next;
		}
	}

	public void put(int key, int value) {
		Node node = null;
		if (!map.containsKey(key)) {
			node = new Node(key, value);
			if (map.size() == cap) {
				map.remove(tail.prev.key);
				removeNode(tail.prev);
			}
		} else {
			node = map.get(key);
			removeNode(node);
			node.val = value;

		}
		insertToHead(node);
		map.put(key, node);
	}

	public void insertToHead(Node n) {
		Node headNext = head.next;
		head.next = n;
		headNext.prev = n;
		n.prev = head;
		n.next = headNext;
	}

	public void removeNode(Node n) {
		n.prev.next = n.next;
		n.next.prev = n.prev;
	}

	public static void main(String a[]) {
		LRUCache l = new LRUCache(2);
		l.put(1, 1);
		l.put(2, 2);
		System.out.println("Res " + l.get(1));
		l.put(3, 3);
		System.out.println("Res " + l.get(2));
		l.put(4, 4);
		System.out.println("Res " + l.get(2));
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
