package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class DefaultTemplateFileService extends JSFFileService {
	private static final String DEFAULT_FTL = "default.ftl";
	private static final String DEFAULT_XHTML = "default.xhtml";

	public DefaultTemplateFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getWebappPath(app.getName()).resolve(Paths.get(TEMPLATES, DEFAULT_XHTML));
	}

	@Override
	public String getTemplateName() {
		return DEFAULT_FTL;
	}
}
