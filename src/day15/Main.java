package day15;

public class Main {
	
	public static void main(String[] args) {
		
		Generator a = new Generator(16807, 277);
		Generator b = new Generator(48271, 349);
		
		int count_a = 0;
		for(int i = 0; i < 40000000; i++){
			long tmp_a = a.generate();
			long tmp_b = b.generate();
			if(((tmp_a << 48) >> 48) == ((tmp_b << 48) >> 48)){		//compare lowest 16 bits
				count_a++;
			}
		}
		
		a.reset();
		b.reset();
		
		int count_b = 0;
		for(int i = 0; i < 5000000; i++){
			long tmp_a = a.generateMultipleOf(4);
			long tmp_b = b.generateMultipleOf(8);
			if(((tmp_a << 48) >> 48) == ((tmp_b << 48) >> 48)){		//compare lowest 16 bits
				count_b++;
			}
		}
		
		System.out.println("Part 1:\t" + count_a);
		System.out.println("Part 2:\t" + count_b);
	}
	
	
}
