package com.boo.common;

public class HitCounter {

	int[] hitsEachSecond;

	HitCounter() {
		hitsEachSecond = new int[300];
	}

	public void hit(int sec) {
		int modSec = sec % 300;
		if (sec != modSec) {
			hitsEachSecond[modSec] = 1;
		} else {
			hitsEachSecond[sec]++;
		}
	}

	public int getHits() {
		int c = 0;
		for (int i = 0; i < 300; i++) {
			c += hitsEachSecond[i];
		}
		return c;
	}

	public static void main(String[] args) {
		HitCounter h = new HitCounter();
		h.hit(1);
		h.hit(2);
		h.hit(3);
		h.hit(4);
		h.hit(300);
		h.hit(301);
	}

}
