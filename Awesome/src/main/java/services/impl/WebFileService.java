package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import services.JSFFileService;
import models.impl.JSFApp;

public class WebFileService extends JSFFileService {
	public WebFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "webapp", "WEB-INF", "web.xml");
	}

	@Override
	public String getTemplateName() {
		return "web.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("app", app);

		return root;
	}
}
