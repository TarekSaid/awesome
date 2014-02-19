package enums;


public enum FieldProperty {
	REQUIRED("nullable = false", "not null");

	private String description;
	private String sql;

	private FieldProperty(String description, String sql) {
		this.description = description;
		this.sql = sql;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
