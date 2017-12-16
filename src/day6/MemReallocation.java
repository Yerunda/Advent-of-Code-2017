package day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MemReallocation {
	private ArrayList<Integer> memory;
	private HashMap<String, Integer> snapshots;

	public MemReallocation(ArrayList<Integer> memory){
		this.memory = memory;
		snapshots = new HashMap<String, Integer>();
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> memory = new ArrayList<Integer>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("memory.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNext()){
			memory.add(in.nextInt());
		}
		in.close();
		MemReallocation mem = new MemReallocation(memory);
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
