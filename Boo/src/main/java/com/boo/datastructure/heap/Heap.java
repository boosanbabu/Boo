package com.boo.datastructure.heap;

import java.util.Arrays;

public class Heap {

	private int[] data;
	private int size;
	private int capacity;

	private int leftChildIdx(int parentIdx) {
		return 2 * parentIdx + 1;
	}

	private int leftChild(int idx) {
		return data[leftChildIdx(idx)];
	}

	private int rightChild(int idx) {
		return data[rightChildIdx(idx)];
	}

	private int rightChildIdx(int parentIdx) {
		return 2 * parentIdx + 2;
	}

	private int parentIdx(int childIdx) {
		return (childIdx - 1) / 2;
	}

	private boolean hasChildren(int parentIdx) {
		return leftChildIdx(parentIdx) < size;
	}

	private int parentVal(int childIdx) {
		return data[parentIdx(childIdx)];
	}

	private boolean hasParent(int idx) {
		return size > 1;
	}

	Heap() {
		this(10);
	}

	Heap(int init) {
		data = new int[init];
		size = 0;
		capacity = init;
	}

	private void ensureCapacity() {
		if (size >= capacity) {
			capacity = capacity << 1;
			data = Arrays.copyOf(data, capacity);

		}
	}

	boolean add(int val) {
		size++;
		ensureCapacity();
		data[size - 1] = val;
		heapifyUp();
		return true;
	}

	private void heapifyUp() {
		int idx = size - 1;

		while (hasParent(idx) && parentVal(idx) > data[idx]) {
			swap(data, idx, parentIdx(idx));
			idx = parentIdx(idx);
		}
	}

	private void swap(int[] data, int i, int j) {
		data[i] = data[i] ^ data[j];
		data[j] = data[i] ^ data[j];
		data[i] = data[i] ^ data[j];
	}

	int remove() throws IllegalAccessException {
		if (size == 0) {
			throw new IllegalAccessException("Empty Heap!");
		}
		int item = data[0];
		data[0] = data[size - 1];
		size--;
		heapifyDown();
		return item;
	}

	private void heapifyDown() {
		int idx = 0;
		while (hasChildren(idx)) {
			int smallChildIdx = leftChildIdx(idx);
			if (rightChildIdx(idx) < size && rightChild(idx) < leftChild(idx)) {
				smallChildIdx = rightChildIdx(idx);
			}
			if (data[idx] < data[smallChildIdx])
				break;

			swap(data, smallChildIdx, idx);
			idx = smallChildIdx;
		}
	}

	int peek() throws IllegalAccessException {
		if (size == 0) {
			throw new IllegalAccessException("Empty Heap!");
		}
		return data[0];
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) throws IllegalAccessException {
		Heap h = new Heap();
		int arr[] = { 1, 3, 4, 5, 7, 8, 9, 10 };
		for (int i : arr) {
			h.add(i);
		}
		h.add(2);
		System.out.println(h.remove());
		h.add(6);
		h.add(12);
		h.add(11);
		System.out.println(h.remove());
		for (int i = 0; i < 10; i++) {
			System.out.println(h.remove());
		}
		System.out.println(h.remove());
	}
}
