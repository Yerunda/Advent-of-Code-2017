package day20;

public class Particle implements Comparable<Particle>{
	private int p_x, p_y, p_z;
	private int v_x, v_y, v_z;
	private int a_x, a_y, a_z;
	private int id;
	
	public Particle(int id, int p[], int v[], int a[]){
		this.id = id;
		this.p_x = p[0];
		this.p_y = p[1];
		this.p_z = p[2];
		this.v_x = v[0];
		this.v_y = v[1];
		this.v_z = v[2];
		this.a_x = a[0];
		this.a_y = a[1];
		this.a_z = a[2];
	}
	
	/**
	 * Move particle
	 */
	public void move(){
		v_x += a_x;
		v_y += a_y;
		v_z += a_z;
		p_x += v_x;
		p_y += v_y;
		p_z += v_z;
	}

	private int magnitudeOfAcceleration(){
		return (int) Math.sqrt((Math.pow(a_x, 2) + Math.pow(a_y, 2) + Math.pow(a_z, 2)));
	}
	
	/**
	 * Get id of the particle
	 * @return id
	 */
	public int id(){
		return id;
	}
	
	/**
	 * Get position of particle
	 * @return a string representation of position
	 */
	public String pos(){
		return new String("x:" + p_x + "y:" + p_y + "z:" + p_z);
	}

	@Override
	public int compareTo(Particle arg0) {
		Particle p = (Particle)arg0;
		return magnitudeOfAcceleration() - p.magnitudeOfAcceleration();
	}
	
}
