package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.JSFApp;
import models.files.impl.ModelFile;
import services.JSFFileService;

public class ModelFileService extends JSFFileService {
	private static final String MODEL = "model";
	private static final String MODEL_FTL = "model.ftl";
	private static final String _JAVA = ".java";
	ModelFile modelFile;

	public ModelFileService(JSFApp app, ModelFile modelFile) {
		super(app);
		this.modelFile = modelFile;
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(MODELS, IMPL, modelFile.getName() + _JAVA));
	}

	@Override
	public String getTemplateName() {
		return MODEL_FTL;
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put(MODEL, modelFile);

		return root;
	}
}
