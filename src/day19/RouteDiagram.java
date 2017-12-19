package day19;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RouteDiagram {
	
	public static final int RIGHT = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;

	private HashMap<Integer, String> hash_matrix;
	private int x;
	private int y;
	private int direction;
	private String path;
	private int row_length;
	private int steps;
	
	public RouteDiagram(ArrayList<String[]> matrix){
		y = 0;
		steps = 0;
		hash_matrix = new HashMap<Integer, String>();
		path = "";
		direction = DOWN;
		if(matrix.size() > 0){
			row_length = matrix.get(0).length;
			for(int i = 0; i < matrix.size(); i++){
				String row[] = matrix.get(i);
				for(int j = 0; j < row.length ; j++){
					if(row[j].matches("-|\\||\\+|[A-Z]")){			//store only squares part of the route in the matrix
						hash_matrix.put(j + i*row.length, row[j]);
					}
				}
			}
			for(int i = 0; i < matrix.get(0).length; i++){
				if(matrix.get(0)[i].equals("|")){
					x = i;
				}
			}
		}else{
			throw new IllegalArgumentException("Matrix must have a size > 0");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String[]> matrix = new ArrayList<String[]>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("day19.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNextLine()){
			String row[] = in.nextLine().split("");
			matrix.add(row);
		}
		in.close();
		RouteDiagram main = new RouteDiagram(matrix);
		main.run();
	}
	
	public void run(){
		while(possible()){
			move();
		}
		System.out.println("Part 1:\t" + path);
		System.out.println("Part 2:\t" + steps);
	}
	
	private void move(){
		switch(direction){
			case RIGHT:
				x++;
				break;
			case LEFT:
				x--;
				break;
			case UP:
				y--;
				break;
			case DOWN:
				y++;
				break;
		}
		String tmp = hash_matrix.get(x + y*row_length);
		if(tmp.matches("[A-Z]")){
			path += tmp;
		}
	}
	
	/**
	 * Check if move is possible. If possible -> sets correct direction.
	 * @return true if move is possible
	 */
	private boolean possible(){
		boolean psb = false;
		switch(direction){
			case RIGHT:
				if(hash_matrix.containsKey(x+1 + y*row_length)){
					psb = true;
				}else if(hash_matrix.containsKey(x + (y+1)*row_length)){
					direction = DOWN;
					psb = true;
				}else if(hash_matrix.containsKey(x + (y-1)*row_length)){
					direction = UP;
					psb = true;
				}
				break;
			case LEFT:
				if(hash_matrix.containsKey(x - 1 + y*row_length)){
					psb = true;
				}else if(hash_matrix.containsKey(x + (y+1)*row_length)){
					direction = DOWN;
					psb = true;
				}else if(hash_matrix.containsKey(x + (y-1)*row_length)){
					direction = UP;
					psb = true;
				}
				break;
			case UP:
				if(hash_matrix.containsKey(x + (y-1)*row_length)){
					psb = true;
				}else if(hash_matrix.containsKey(x + 1 + (y)*row_length)){
					direction = RIGHT;
					psb = true;
				}else if(hash_matrix.containsKey(x - 1 + (y)*row_length)){
					direction = LEFT;
					psb = true;
				}
				break;
			case DOWN:
				if(hash_matrix.containsKey(x + (y+1)*row_length)){
					psb = true;
				}else if(hash_matrix.containsKey(x + 1 + y*row_length)){
					direction = RIGHT;
					psb = true;
				}else if(hash_matrix.containsKey(x - 1 + (y)*row_length)){
					direction = LEFT;
					psb = true;
				}
				break;
		}
		steps++;			//last step that will generate false also needs to be counted
		return psb;
	}
	
}
