package models.impl;

import java.util.List;
import java.util.Set;

import models.files.impl.PlainField;
import models.web.impl.ViewAction;
import enums.BeanScope;

public class Model {
	private String name;
	private BeanScope scope;
	private String title;
	private boolean mainPage;
	private boolean viewOnly;
	private boolean persisted;
	private Set<PlainField> fields;
	private List<ViewAction> actions;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public BeanScope getScope() {
		return scope;
	}

	public void setScope(final BeanScope scope) {
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public boolean isMainPage() {
		return mainPage;
	}

	public void setMainPage(final boolean mainPage) {
		this.mainPage = mainPage;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public boolean isPersisted() {
		return persisted;
	}

	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

	public Set<PlainField> getFields() {
		return fields;
	}

	public void setFields(final Set<PlainField> fields) {
		this.fields = fields;
	}

	public List<ViewAction> getActions() {
		return actions;
	}

	public void setActions(final List<ViewAction> actions) {
		this.actions = actions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Model other = (Model) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
