package enums;

public enum BeanScope {
	SESSION("SessionScoped"),
	VIEW("ViewScoped");

	private BeanScope(String description) {
		this.description = description;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
