package enums;

public enum PomScope {
	COMPILE("compile"),
	PROVIDED("provided"),
	RUNTIME("runtime"),
	TEST("test"),
	SYSTEM("system"),
	IMPORT("import");

	private String description;

	private PomScope(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
