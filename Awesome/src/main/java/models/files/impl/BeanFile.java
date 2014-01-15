package models.files.impl;

import java.util.Set;

import models.files.FileNameRestriction;
import enums.BeanScope;

public class BeanFile extends FileNameRestriction {
	private BeanScope scope;
	private Set<BeanField> fields;

	public BeanFile(String name, BeanScope scope, Set<BeanField> fields) {
		this.name = name;
		this.scope = scope;
		this.fields = fields;
	}

	public BeanScope getScope() {
		return scope;
	}

	public void setScope(final BeanScope scope) {
		this.scope = scope;
	}

	public Set<BeanField> getFields() {
		return fields;
	}

	public void setFields(final Set<BeanField> fields) {
		this.fields = fields;
	}
}
