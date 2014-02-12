package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.FileService;
import services.JSFFileService;
import services.utils.FileServiceUtils;

public class CssFileService extends JSFFileService implements FileService {
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
