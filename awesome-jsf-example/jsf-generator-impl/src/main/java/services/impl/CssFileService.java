package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class CssFileService extends JSFFileService {
	private static final String CSS_FTL = "css.ftl";
	private static final String DEFAULT_CSS = "default.css";
	private static final String CSS = "css";

	public CssFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getWebappPath(app.getName()).resolve(Paths.get(CSS, DEFAULT_CSS));
	}

	@Override
	public String getTemplateName() {
		return CSS_FTL;
	}
}
