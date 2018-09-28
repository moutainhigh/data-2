package com.hourz.leetcode.server.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-28
 */
public class FindSubstring {

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return result;
		}
		Map<String, Integer> wordsCount = generateCount(words);
		int length = words[0].length();
		for (int i = 0; i < length; ++i) {
			Map<String, Integer> window = new HashMap<>();
			int left = i;
	        int right = i;
	        while (right <= s.length() - length && left <= s.length() - length * words.length) {
	        	String sub = s.substring(right, right + length);
	        	incr(window, sub);
	        	if (!wordsCount.containsKey(sub)) {
	            	window.clear();
	            	right += length;
	            	left = right;
	            	continue;
	        	}
	        	while (window.get(sub) > wordsCount.get(sub)) {
	        		decr(window, s.substring(left, left + length));
	        		left += length;
	        	}
	        	right += length;
	        	if (right - left == length * words.length) {
	        		result.add(left);
	        	}
	        }
		}
		return result;
	}

	public static void main(String[] args) {
		FindSubstring findSubstring = new FindSubstring();
		String s = "wordgoodstudentgoodword";
		String[] words = {"word","good"};
		System.out.println(findSubstring.findSubstring(s,words));
	}
	private Map<String, Integer> generateCount(String[] words) {
		Map<String, Integer> wordsCount = new HashMap<>();
		for (String word : words) {
			incr(wordsCount, word);
		}
		return wordsCount;
	}

	private void incr(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}

	private void decr(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			Integer value = map.get(key);
			if (value <= 1) {
				map.remove(key);
			} else {
				map.put(key, value - 1);
			}
		}
	}
}
