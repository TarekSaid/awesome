package br.com.revo.awesome.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import br.com.revo.awesome.models.impl.BeanFile;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.models.impl.PomFile;
import br.com.revo.awesome.services.JSFFileService;

public class BeanFileService extends JSFFileService {
	private BeanFile beanFile;

	public BeanFileService(JSFApp app, BeanFile beanFile) {
		super(app);

		this.beanFile = beanFile;
	}

	@Override
	public Path getPath() {
		PomFile pomFile = app.getPomFile();
		return Paths.get(app.getName(), "src", "main", "java", pomFile.getGroupId().replaceAll("\\.", "/"), pomFile.getArtifactId().toLowerCase(), "models", beanFile.getName() + "Bean.java");
	}

	@Override
	public String getTemplateName() {
		return "bean.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("bean", beanFile);
		root.put("pom", app.getPomFile());

		return root;
	}
}
