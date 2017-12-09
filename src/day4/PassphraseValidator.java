package day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class PassphraseValidator {
	
	private final int[] PRIMES = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
	
	public static void main(String[] args) {
		PassphraseValidator main = new PassphraseValidator();
		main.run("passphrases.txt");
	}
	
	public void run(String filename){
		System.out.println("Part 1:\t" + countNonDuplicates(filename));
		System.out.println("Part 2:\t" + countNonAnagrams(filename));
	}
	
	/**
	 * Part One
	 */
	private int countNonDuplicates(String filename){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int count = 0;
		HashSet<String> set = new HashSet<String>();
		while(in.hasNextLine()){
			String line = in.nextLine();
			String[] words = line.split("\\s");
			for(String word : words){
				set.add(word);
			}
			count = set.size() == words.length ? count + 1 : count;
			set.clear();
		}
		return count;
	}
	
	/**
	 * Part Two
	 */
	private int countNonAnagrams(String filename){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int count = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		while(in.hasNextLine()){
			String line = in.nextLine();
			String[] words = line.split("\\s");
			for(String word : words){
				int product = 1;
				for(char c : word.toCharArray()){
					product *= letterToPrime(c);
				}
				set.add(product);
			}
			count = set.size() == words.length ? count + 1 : count;
			set.clear();
		}
		return count;
	}
	
	private int letterToPrime(char c){
		int value = (int)c;
		if(value < 123 && value > 96){
			return PRIMES[(int)c - 97];
		}else{
			throw new IllegalArgumentException("Character " + c + " not allowed. Character must be lowercae: a-z");
		}
	}
}
