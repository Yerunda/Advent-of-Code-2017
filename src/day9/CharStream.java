package day9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CharStream {

	private final char GROUP_OPEN = '{';
	private final char GROUP_CLOSE = '}';
	private final char GARBAGE_OPEN = '<';
	private final char GARBAGE_CLOSE = '>';
	private final char NEXT_IGNORE = '!';

	private ArrayList<Character> stream;
	private int score;
	private int garbageCount;

	public CharStream(String filename){
		stream = new ArrayList<Character>();
		score = 0;
		garbageCount = 0;
		Scanner in = null;

		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(in.hasNext()){
			for(char c : in.next().toCharArray()){
				stream.add(c);
			}
		}
	}

	public static void main(String[] args) {
		CharStream stream = new CharStream("stream.txt");
		stream.run();
	}

	public void run(){
		int nLvl = 0;
		boolean garbage = false;
		for(int i = 0; i < stream.size(); i++){
			switch(stream.get(i)){
				case GROUP_OPEN:
					if(!garbage){
						nLvl++;
					}else{
						garbageCount++;
					}
					break;
				case GROUP_CLOSE:
					if(!garbage){
						score += nLvl;
						nLvl--;
					}else{
						garbageCount++;
					}
					break;
				case GARBAGE_OPEN:
					if(garbage){
						garbageCount++;
					}else{
						garbage = true;
					}
					break;
				case GARBAGE_CLOSE:
					garbage = false;
					break;
				case NEXT_IGNORE:
					i++;
					break;
				default:
					garbageCount = garbage ? garbageCount + 1 : garbageCount;
					break;
			}
		}
		System.out.println("Score:\t\t" + score);
		System.out.println("Garbage:\t" + garbageCount);
	}



}
