package models.web.impl;

import enums.ActionType;

public class ViewAction {
	private ActionType actionType;
	private String value;

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
