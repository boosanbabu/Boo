package com.boo.google.questions;

import java.util.*;

public class DecodeString {
	public String decodeString(String s) {
		Deque<Character> countStack = new LinkedList<>();
		Deque<Character> stringStack = new LinkedList<>();
		boolean number = true;
		for (char c : s.toCharArray()) {

		}
		return s;
	}

	public static void main(String[] args) {
		DecodeString d = new DecodeString();
		d.decodeString("2[abc]3[cd]ef");
	}

}
