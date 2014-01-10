package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import services.JSFFileService;
import models.files.impl.ViewFile;
import models.impl.JSFApp;

public class ViewFileService extends JSFFileService {
	private ViewFile viewFile;
	public ViewFileService(JSFApp app, ViewFile viewFile) {
		super(app);
		this.viewFile = viewFile;
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "webapp", viewFile.getName().concat(".xhtml"));
	}

	@Override
	public String getTemplateName() {
		return "view.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("view", viewFile);

		return root;
	}
}
