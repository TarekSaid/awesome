package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.files.impl.ModelFile;
import models.impl.JSFApp;
import services.JSFFileService;

public class ModelFileService extends JSFFileService {
	ModelFile modelFile;

	public ModelFileService(JSFApp app, ModelFile modelFile) {
		super(app);
		this.modelFile = modelFile;
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get("models", "impl", modelFile.getName() + ".java"));
	}

	@Override
	public String getTemplateName() {
		return "model.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("model", modelFile);

		return root;
	}
}
