package pl.edu.uj.ii.goofy.algorithm;

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
	
	public String toString() {
		if (type == DuDescriptionType.Definition) {
			return "d " + name;
		} else  {
			return "u " + name;
		}
	}
}
