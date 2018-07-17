
/**
 * Given a String as input check if that can be transformed to valid palindrome sequence
 * Output -   Yes/ No
 */
package com.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheckStringPalindromTransformation {
	
	
	public boolean checkPalindromeTransform(String input) {
		
		Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		for(int i=0;i<input.length();++i) {
			Character token = input.charAt(i);
			if(frequencyMap.containsKey(token))
			{
				frequencyMap.put(token, frequencyMap.get(token) + 1);
			}
			else {
				frequencyMap.put(token, 1);
			}
		}
		
		Set<Character> keys =  frequencyMap.keySet();
		int countOdd = 0;
		
		for (Character character : keys) {
			
			
			int frequency = frequencyMap.get(character);
			if(frequency % 2 ==1) {
				++countOdd;
			}
		}
		
		if(countOdd > 1) {
			return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] args ) {
		CheckStringPalindromTransformation ob = new CheckStringPalindromTransformation();
		if(ob.checkPalindromeTransform("aarro")) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
		
	}
	
	

}
