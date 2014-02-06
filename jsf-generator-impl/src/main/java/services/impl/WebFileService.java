package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class WebFileService extends JSFFileService {
	private static final String WEB_XML = "web.xml";
	private static final String WEB_INF = "WEB-INF";
	private static final String WEB_FTL = "web.ftl";

	public WebFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return WEBAPP_PATH.resolve(Paths.get(WEB_INF, WEB_XML));
	}

	@Override
	public String getTemplateName() {
		return WEB_FTL;
	}
}
