package day11;

public class Hexgrid {
	
	public static final String DIRECTION_NE = "ne";
	public static final String DIRECTION_N = "n";
	public static final String DIRECTION_NW = "nw";
	public static final String DIRECTION_SE = "se";
	public static final String DIRECTION_S = "s";
	public static final String DIRECTION_SW = "sw";
	
	private int x, y, z, max;
	
	/**
	 * Hexagonal grid represented by cube coordinates
	 * with starting point at (0,0,0)
	 */
	public Hexgrid(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public void move(String direction){
		switch(direction){
			case DIRECTION_NE: 
				x++;
				z--;
				break;
			case DIRECTION_N:
				y++;
				z--;
				break;
			case DIRECTION_NW: 
				y++;
				x--;
				break;
			case DIRECTION_SE:
				y--;
				x++;
				break;
			case DIRECTION_S:
				z++;
				y--;
				break;
			case DIRECTION_SW:
				z++;
				x--;
				break;
			default:
				throw new UnsupportedOperationException("Direction: \'" + direction + "\' is not supported");
		}
		max = minSteps() > max ? minSteps() : max;
	}
	
	public int minSteps(){
		return (Math.abs(x) + Math.abs(y) + Math.abs(z))/2;
	}
	
	public int max(){
		return max;
	}
	
}
