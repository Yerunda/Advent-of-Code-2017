package day20;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("day20.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Particle> particles = new ArrayList<Particle>();
		int id = 0;
		while(in.hasNextLine()){
			String line[] = in.nextLine().split(", ");
			String str_p[] = line[0].replaceAll("[a-z]|=|<|\\s|>", "").split(",");
			String str_v[] = line[1].replaceAll("[a-z]|=|<|\\s|>", "").split(",");
			String str_a[] = line[2].replaceAll("[a-z]|=|<|\\s|>", "").split(",");
			int p[] = Arrays.asList(str_p).stream()
					.mapToInt(Integer::parseInt)
					.toArray();
			int v[] = Arrays.asList(str_v).stream()
					.mapToInt(Integer::parseInt)
					.toArray();
			int a[] = Arrays.asList(str_a).stream()
					.mapToInt(Integer::parseInt)
					.toArray();
			particles.add(new Particle(id, p, v, a));	
			id++;
		}
		in.close();

		int length = particles.size();
		int prev_length = particles.size();
		int no_collision = 0;
		Collections.sort(particles);
		int closest_prt = particles.get(0).id();
		
		while(no_collision < 1000){		//assume no more collisions will take place if none have occured for 1000 moves
			for(int i = 0; i < particles.size(); i++){
				particles.get(i).move();
			}
			Map<String, Long> prt_on_pos = particles.stream()			//map position to occurences of particles on that position
					.flatMap(prt -> Stream.of(prt.pos()))
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			particles = particles.stream().filter(prt -> prt_on_pos.get(prt.pos()) == 1).collect(Collectors.toList());	//filter out all particles on positions where collision occured
			length = particles.size();
			if(length == prev_length){	//check if collision has occured
				no_collision++;
			}else{
				no_collision = 0;
			}
			prev_length = length;
		}
		
		System.out.println("Part 1:\t" + closest_prt);
		System.out.println("Part 2:\t" + particles.size());
		
	}
	
	
	
}
