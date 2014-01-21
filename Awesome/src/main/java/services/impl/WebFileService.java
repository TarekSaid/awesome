package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.JSFFileService;

public class WebFileService extends JSFFileService {
	public WebFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return WEBAPP_PATH.resolve(Paths.get("WEB-INF", "web.xml"));
	}

	@Override
	public String getTemplateName() {
		return "web.ftl";
	}
}
