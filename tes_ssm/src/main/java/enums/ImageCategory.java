package enums;

public enum ImageCategory {
	USER("user"),COURSE("course");
	
	private String name;

	private ImageCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
