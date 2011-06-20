package pl.edu.uj.ii.goofy.algorithm;

import java.util.Collection;
import java.util.LinkedList;

public class Node {
	private final short id;
	private LinkedList<DuDescription> usages;
	
	public Node(short id, Collection<DuDescription> usages) {
		this.id = id;
		this.usages = new LinkedList<DuDescription>(usages);
	}
	
	public Node(short id) {
		this.id = id;
		this.usages = new LinkedList<DuDescription>();
	}
	
	public short getId() {
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
		if (obj.getClass() != Node.class) {
			return false;
		}
		
		return this.id == ((Node)obj).id;
	}

	@Override
	public String toString() {
		return Integer.toString(id);
	}
	
	
}
