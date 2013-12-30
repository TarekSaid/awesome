package br.com.revo.awesome.models.files.impl;

import br.com.revo.awesome.models.files.FileNameRestriction;
import br.com.revo.awesome.models.web.impl.ViewContent;

public class ViewFile extends FileNameRestriction {
	private boolean welcomeFile;
	private ViewContent content;

	public ViewContent getContent() {
		return content;
	}

	public void setContent(ViewContent content) {
		this.content = content;
	}

	public boolean isWelcomeFile() {
		return welcomeFile;
	}

	public void setWelcomeFile(boolean welcomeFile) {
		this.welcomeFile = welcomeFile;
	}
}
