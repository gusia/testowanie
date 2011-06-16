package pl.edu.uj.ii.goofy;

public class EdgeIdGenerator {
	private static EdgeIdGenerator instance;
	private int id;
	
	public static EdgeIdGenerator getInstance() {
		if (instance == null) {
			instance = new EdgeIdGenerator();
		}
		
		return instance;
	}
	
	public int getId() {
		return id++;
	}
	
	private EdgeIdGenerator() {
		id = 0;
	}
}
