package pl.edu.uj.ii.goofy.algorithm;

import java.util.Collection;
import java.util.LinkedList;

public class Edge {
	
	private final int id;
	private LinkedList<DuDescription> usages;
	
	public Edge(short first, short second, Collection<DuDescription> usages) {
		this.id = calcId(first, second);
		this.usages = new LinkedList<DuDescription>(usages);
	}
	
	public Edge(short first, short second) {
		this.id = calcId(first, second);
		this.usages = new LinkedList<DuDescription>();
	}
	
	public int getId() {
		return id;
	}

	public LinkedList<DuDescription> getUsages() {
		return usages;
	}
	
	public void addUsage(DuDescription usage) {
		usages.add(usage);
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Edge.class) {
			return false;
		}
		
		return this.id == ((Edge)obj).id;
	}

	@Override
	public String toString() {
		return Integer.toString(id);
	}
	
	private static synchronized int calcId(short first, short second) {
		return ((int)first << 16) + second;
	}
}
