package com.boo.leetcode.problems;

import java.util.*;

public class Solution {

	public int countLargestGroup(int n) {
		Map<Integer, Integer> sizeMap = new HashMap<>();
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int sumOfDigits = sumOfDigits(i);
			sizeMap.put(sumOfDigits, sizeMap.getOrDefault(sumOfDigits, 0) + 1);
			max = Math.max(sizeMap.get(sumOfDigits), max);
		}
		int res = 0;
		for (Integer k : sizeMap.keySet()) {
			if (sizeMap.get(k) == max)
				res++;
		}
		return res;
	}

	public static int sumOfDigits(int i) {
		int sum = 0;
		while (i > 0) {
			int r = i % 10;
			sum += r;
			i = i / 10;
		}
		return sum;
	}

	public boolean canConstruct(String s, int k) {
		if (s.length() < k)
			return false;
		if (s.length() == k)
			return true;

		Map<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}
		int[] count = new int[2];
		for (Character c : freqMap.keySet()) {
			int freq = freqMap.get(c);
			if (freq % 2 == 0)
				count[0]++;
			else
				count[1]++;
		}

		return count[1] <= k;
	}

	public class Circle {
		Point centre;
		int r;

		Circle(int x0, int y0, int r) {
			centre = new Point(x0, y0);
			this.r = r;
		}
	}

	public class Rectangle {
		Point a, b, c, d;

		Rectangle(int x1, int y1, int x2, int y2) {
			a = new Point(x2, y1);
			b = new Point(x2, y2);
			c = new Point(x1, y1);
			d = new Point(x1, y2);
		}

	}

	public class Point {
		double x, y;

		Point(int i, int j) {
			x = i;
			y = j;
		}

		public Point(double x2, double y2) {
			x = x2;
			y = y2;
		}
	}

	public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
		Circle circle = new Circle(x_center, y_center, radius);
		Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
		return (pointInRectangle(circle.centre, rectangle) || intersectCircle(circle, rectangle.a, rectangle.b)
				|| intersectCircle(circle, rectangle.c, rectangle.d)
				|| intersectCircle(circle, rectangle.b, rectangle.c)
				|| intersectCircle(circle, rectangle.d, rectangle.a));
	}

	private boolean intersectCircle(Circle c, Point p1, Point p2) {
		double[] abc = lineFromPoints(p1, p2);
		Point foot = findFoot(abc[0], abc[1], abc[2], c.centre.x, c.centre.y);
		return false;
	}

	Point findFoot(double a, double b, double c, double x1, double y1) {
		double temp = -1 * (a * x1 + b * y1 + c) / (a * a + b * b);
		double x = temp * a + x1;
		double y = temp * b + y1;
		return new Point(x, y);
	}

	static double[] lineFromPoints(Point P, Point Q) {
		double a = Q.y - P.y;
		double b = P.x - Q.x;
		double c = a * (P.x) + b * (P.y);
		return new double[] { a, b, c };
	}

	private boolean pointInRectangle(Point p, Rectangle rect) {
		double ap = distance(p, rect.a);
		double ab = distance(rect.b, rect.a);
		double ad = distance(rect.d, rect.a);
		return 0 <= ap * ab && ap * ab <= (ab * ab) && (0 <= (ap * ad) && (ap * ad) <= (ad * ad));
	}

	public double distance(Point p1, Point p2) {
		return calculateDistanceBetweenPoints(p1.x, p1.y, p2.x, p2.y);
	}

	public double calculateDistanceBetweenPoints(double x, double y, double x2, double y2) {
		return Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x));
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		s.checkOverlap(1, 1, 1, -3, -3, 3, 3);
		System.out.println(s.canConstruct("messi", 3));
	}

}
