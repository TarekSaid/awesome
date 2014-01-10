package models.files.impl;

import java.util.ArrayList;
import java.util.List;

import models.files.FileNameRestriction;
import enums.BeanScope;

public class BeanFile extends FileNameRestriction {
	private BeanScope scope;
	private List<BeanField> fields = new ArrayList<>();

	public BeanScope getScope() {
		return scope;
	}

	public void setScope(BeanScope scope) {
		this.scope = scope;
	}

	public List<BeanField> getFields() {
		return fields;
	}

	public void setFields(List<BeanField> fields) {
		this.fields = fields;
	}
}
