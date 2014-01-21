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
		StringBuilder fileName = new StringBuilder(viewFile.getName());

		if (viewFile.isCrud()) {
			fileName.append("s");
		}

		fileName.append(".xhtml");
		
		return Paths.get(app.getName(), "src", "main", "webapp", fileName.toString());
	}

	@Override
	public String getTemplateName() {
		return viewFile.isCrud() ? "crud.ftl" : "view.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("view", viewFile);

		return root;
	}
}
