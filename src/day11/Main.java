package day11;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Hexgrid grid = new Hexgrid();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("path.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNextLine()){
			String[] directions = in.nextLine().split(",");
			for(String direction : directions){
				grid.move(direction);
			}
		}
		in.close();
		System.out.println("Part 1:\t" + grid.minSteps());
		System.out.println("Part 2:\t" + grid.max());
	}
}
