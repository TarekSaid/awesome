package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class CrudFileService extends JSFFileService {
	private static final String CRUD_TEMPLATE_FTL = "crud-template.ftl";
	private static final String CRUD_XHTML = "crud.xhtml";

	public CrudFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getWebappPath(app.getName()).resolve(Paths.get(TEMPLATES, CRUD_XHTML));
	}

	@Override
	public String getTemplateName() {
		return CRUD_TEMPLATE_FTL;
	}
}
