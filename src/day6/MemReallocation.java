package day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MemReallocation {
	private ArrayList<Integer> memory;
	private HashMap<String, Integer> snapshots;

	public MemReallocation(String filename){
		memory = new ArrayList<Integer>();
		snapshots = new HashMap<String, Integer>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNext()){
			memory.add(in.nextInt());
		}
	}
	
	public static void main(String[] args) {
		MemReallocation mem = new MemReallocation("memory.txt");
		mem.run();
		
	}
	
	public void run(){
		int count = 0;
		String snapshot = memory.toString();
		while(!snapshots.containsKey(snapshot)){
			snapshots.put(snapshot, count);
			reallocate();
			snapshot = memory.toString();
			count++;
		}
		System.out.println("Total cycles:\t" + count);
		System.out.println("Cycles diff:\t" + (count-snapshots.get(snapshot)));
	}
	
	private void reallocate(){
		int index = 0;
		for(int i = 1; i < memory.size(); i++){
			if(memory.get(i) > memory.get(index)){
				index = i;
			}
		}
		int blocks = memory.get(index);
		memory.set(index, 0);
		for(int i = (index + 1)%memory.size(); blocks > 0; i = (i+1)%memory.size()){
			int b = (int)Math.ceil(blocks/(memory.size()*1.0));
			memory.set(i, memory.get(i)+b);
			blocks -= b;
		}
	}
}
