package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import services.JSFFileService;
import models.JSFApp;
import models.files.impl.ViewFile;

public class ViewFileService extends JSFFileService {
	private static final String VIEW = "view";
	private static final String VIEW_FTL = "view.ftl";
	private static final String CRUD_FTL = "crud.ftl";
	private static final String XHTML = ".xhtml";
	private static final String S = "s";
	private ViewFile viewFile;
	public ViewFileService(JSFApp app, ViewFile viewFile) {
		super(app);
		this.viewFile = viewFile;
	}

	@Override
	public Path getPath() {
		StringBuilder fileName = new StringBuilder(viewFile.getName());

		if (viewFile.isCrud()) {
			fileName.append(S);
		}

		fileName.append(XHTML);
		
		return WEBAPP_PATH.resolve(Paths.get(fileName.toString()));
	}

	@Override
	public String getTemplateName() {
		return viewFile.isCrud() ? CRUD_FTL : VIEW_FTL;
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put(VIEW, viewFile);

		return root;
	}
}
