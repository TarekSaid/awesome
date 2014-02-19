package models.files.impl;

import java.util.List;
import java.util.Set;

import models.files.FileNameRestriction;
import models.web.impl.ViewAction;

public class ViewFile extends FileNameRestriction {
	private String title;
	private boolean welcomeFile;
	private boolean crud;
	private Set<PlainField> fields;
	private List<ViewAction> actions;

	public ViewFile(String name, String title, boolean welcomeFile, boolean crud, Set<PlainField> fields, List<ViewAction> actions) {
		this.name = name;
		this.title = title;
		this.welcomeFile = welcomeFile;
		this.crud = crud;
		this.fields = fields;
		this.actions = actions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isWelcomeFile() {
		return welcomeFile;
	}

	public void setWelcomeFile(boolean welcomeFile) {
		this.welcomeFile = welcomeFile;
	}

	public boolean isCrud() {
		return crud;
	}

	public void setCrud(boolean crud) {
		this.crud = crud;
	}

	public Set<PlainField> getFields() {
		return fields;
	}

	public void setFields(Set<PlainField> fields) {
		this.fields = fields;
	}

	public List<ViewAction> getActions() {
		return actions;
	}

	public void setActions(List<ViewAction> actions) {
		this.actions = actions;
	}
}
