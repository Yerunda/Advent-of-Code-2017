package day15;

public class Generator {
	
	private int factor;
	private long value;
	private int seed;
	
	/**
	 * Number generator 
	 * @param factor
	 * @param seed
	 */
	public Generator(int factor, int seed){
		this.factor = factor;
		this.seed = seed;
		value = seed;
	}
	
	public long generate(){
		value = (value*factor)%Integer.MAX_VALUE;
		return value;
	}
	
	public long generateMultipleOf(int multiple){
		value = (value*factor)%Integer.MAX_VALUE;
		while(value % multiple != 0){
			value = (value*factor)%Integer.MAX_VALUE;
		}
		return value;
	}
	
	public void reset(){
		value = seed;
	}
}
