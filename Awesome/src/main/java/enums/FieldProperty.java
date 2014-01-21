package enums;

public enum FieldProperty {
	REQUIRED("nullable = false");

	private String description;

	private FieldProperty(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
