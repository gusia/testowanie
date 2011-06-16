package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.LinkedList;

public interface TestRequirementInt<N, E> {
	public LinkedList<LinkedList<N>> getRequirement();
}
