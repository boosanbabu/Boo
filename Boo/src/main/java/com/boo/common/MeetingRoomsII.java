package com.boo.common;

import java.util.Arrays;

import com.boo.common.MeetingRoomsII.Event.EventType;

public class MeetingRoomsII {
	public static class Event {
		int time;
		EventType type;

		enum EventType {
			START, END;
		}

		Event(int time, EventType type) {
			this.time = time;
			this.type = type;
		}
	}

	public int minMeetingRooms(int[][] intervals) {
		Event[] list = new Event[intervals.length * 2];
		int i = 0;
		for (int[] interval : intervals) {
			list[i++] = new Event(interval[0], MeetingRoomsII.Event.EventType.START);
			list[i++] = new Event(interval[1], MeetingRoomsII.Event.EventType.END);
		}
		int r = 0, min = 0;
		Arrays.sort(list, (a, b) -> a.time == b.time ? a.type == EventType.END ? -1 : 1 : a.time - b.time);
		for (Event e : list) {
			if (e.type == EventType.START)
				r++;
			else if (e.type == EventType.END)
				r--;
			min = Math.max(r, min);
		}
		return min;
	}

	public static void main(String[] args) {
		MeetingRoomsII m = new MeetingRoomsII();
		int[][] intervals = { { 2, 15 }, { 2, 15 }, { 2, 15 }, { 2, 15 }, { 2, 15 }, { 2, 15 }, { 2, 15 }, { 36, 45 },
				{ 9, 29 }, { 16, 23 }, { 4, 9 } };
		System.out.println(m.minMeetingRooms(intervals));
	}
}
