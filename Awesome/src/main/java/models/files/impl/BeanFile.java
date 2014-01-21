package models.files.impl;

import java.util.Set;

import models.files.FileNameRestriction;
import enums.BeanScope;

public class BeanFile extends FileNameRestriction {
	private BeanScope scope;
	private Set<PlainField> fields;
	private boolean mediator;

	public BeanFile(String name, BeanScope scope, Set<PlainField> fields, boolean mediator) {
		this.name = name;
		this.scope = scope;
		this.fields = fields;
		this.mediator = mediator;
	}

	public BeanScope getScope() {
		return scope;
	}

	public void setScope(final BeanScope scope) {
		this.scope = scope;
	}

	public Set<PlainField> getFields() {
		return fields;
	}

	public void setFields(final Set<PlainField> fields) {
		this.fields = fields;
	}

	public boolean isMediator() {
		return mediator;
	}

	public void setMediator(boolean mediator) {
		this.mediator = mediator;
	}
}
