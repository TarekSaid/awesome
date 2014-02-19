package models.files.impl;

import java.util.Set;

import models.files.FileNameRestriction;

public class ModelFile extends FileNameRestriction {
	private Set<PlainField> fields;

	public ModelFile(String name, Set<PlainField> fields) {
		this.name = name;
		this.fields = fields;
	}

	public Set<PlainField> getFields() {
		return fields;
	}

	public void setFields(Set<PlainField> fields) {
		this.fields = fields;
	}
}
