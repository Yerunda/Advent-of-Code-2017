package day8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class VirtualMachine {
	
	private final String GT = ">";
	private final String LT = "<";
	private final String GTE = ">=";
	private final String LTE = "<=";
	private final String EQ = "==";
	private final String NEQ = "!=";
	
	private int highest;
	private HashMap<String, Integer> memory;
	private ArrayList<String> instructions;
	
	public VirtualMachine(String filename){
		memory = new HashMap<String, Integer>();
		instructions = new ArrayList<String>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNextLine()){
			String line = in.nextLine();
			instructions.add(line);
			memory.put(line.substring(0, line.indexOf(" ")), 0);
		}
		in.close();
	}
	
	public static void main(String[] args) {
		VirtualMachine vm = new VirtualMachine("instructions.txt");
		vm.run();
	}
	
	public void run(){
		highest = 0;
		for(String instr : instructions){
			interpret(instr);
		}
		ArrayList<Integer> values = new ArrayList<Integer>(memory.values());
		Collections.sort(values);
		System.out.println("Largest value at end:\t" + values.get(values.size()-1));
		System.out.println("Largest value overall:\t" + highest);
	}

	private void interpret(String instr){
		//TODO: validate instruction syntax
		String op_stmt[] = instr.split("if\\s");
		String operation[] = op_stmt[0].split("\\s");
		String statement[] = op_stmt[1].split("\\s");
		int op1 = memory.get(statement[0]);
		int op2 = Integer.parseInt(statement[2]);
		String operator = statement[1];
		
		switch(operator){
			case GT: 
				if(op1 > op2){
					execute(operation);
				}
				break;
			case GTE: 
				if(op1 >= op2){
					execute(operation);
				}
				break;
			case LTE: 
				if(op1 <= op2){
					execute(operation);
				}
				break;
			case LT: 
				if(op1 < op2){
					execute(operation);
				}
				break;
			case EQ: 
				if(op1 == op2){
					execute(operation);
				}
				break;
			case NEQ: 
				if(op1 != op2){
					execute(operation);
				}
				break;
		}
		
	}
	
	private void execute(String operation[]){
		int value = memory.get(operation[0]);
		if(operation[1].equals("inc")){
			value += Integer.parseInt(operation[2]);
			highest = value > highest ? value : highest;
			memory.put(operation[0], value);
		}else if(operation[1].equals("dec")){
			value -= Integer.parseInt(operation[2]);
			memory.put(operation[0], value);
		}else{
			throw new UnsupportedOperationException("Operation not supported: " + operation[1]);
		}
	}
	
}
