package models.files.impl;

import java.util.List;

import models.files.FileNameRestriction;
import models.web.impl.ViewAction;

public class ViewFile extends FileNameRestriction {
	private String title;
	private boolean welcomeFile;
	private List<ViewAction> actions;

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

	public List<ViewAction> getActions() {
		return actions;
	}

	public void setActions(List<ViewAction> actions) {
		this.actions = actions;
	}
}
