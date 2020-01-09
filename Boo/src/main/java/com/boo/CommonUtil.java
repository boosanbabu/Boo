package com.boo;

import java.util.List;

public class CommonUtil {

	public static void printListOfList(List<List<Integer>> res) {
		res.forEach(list -> {
			list.forEach(i -> {
				System.out.print(i + " ");
			});
			System.out.println();
		});
	}

}
