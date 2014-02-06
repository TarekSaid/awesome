package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.JSFApp;
import models.files.impl.BeanFile;
import services.JSFFileService;

public class BeanFileService extends JSFFileService {
	private static final String BEAN_JAVA = "Bean.java";
	private static final String CONTROLLERS = "controllers";
	private static final String BEAN = "bean";
	private static final String BEAN_FTL = "bean.ftl";
	private static final String CONTROLLER = "controller.ftl";
	private BeanFile beanFile;

	public BeanFileService(JSFApp app, BeanFile beanFile) {
		super(app);

		this.beanFile = beanFile;
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(CONTROLLERS, beanFile.getName() + BEAN_JAVA));
	}

	@Override
	public String getTemplateName() {
		return beanFile.isMediator() ? CONTROLLER : BEAN_FTL;
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put(BEAN, beanFile);
		root.put(POM, app.getPomFile());

		return root;
	}
}
