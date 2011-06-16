package pl.edu.uj.ii.goofy.algorithm.coverage;

public class DuDescription {
	private String name;
	private DuDescriptionType type;
	
	public DuDescription(String name, DuDescriptionType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public DuDescriptionType getType() {
		return type;
	}
}
