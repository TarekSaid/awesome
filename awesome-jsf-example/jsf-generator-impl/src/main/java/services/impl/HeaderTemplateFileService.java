package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class HeaderTemplateFileService extends JSFFileService {
	private static final String HEADER_FTL = "header.ftl";
	private static final String HEADER_XHTML = "header.xhtml";
	private static final String DEFAULT = "default";

	public HeaderTemplateFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getWebappPath(app.getName()).resolve(Paths.get(TEMPLATES, DEFAULT, HEADER_XHTML));
	}

	@Override
	public String getTemplateName() {
		return HEADER_FTL;
	}
}
