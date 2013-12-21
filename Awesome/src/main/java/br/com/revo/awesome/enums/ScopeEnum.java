package br.com.revo.awesome.enums;

public enum ScopeEnum {
	SESSION("SessionScoped");

	private ScopeEnum(String description) {
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
