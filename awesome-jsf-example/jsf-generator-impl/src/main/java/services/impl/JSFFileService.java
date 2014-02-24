package services.impl;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import services.FileService;
import controllers.ResourceLoader;
import models.impl.JSFApp;
import enums.FreeMarkerConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class JSFFileService implements FileService {
	protected JSFApp app;
	private Configuration cfg = FreeMarkerConfiguration.INSTANCE.getConfiguration();

	private static final String WRITER_ERROR = "jsf.file.service.writer.error";
	private static final String CREATE_FILE_ERROR = "jsf.file.service.create.file.error";
	private static final String FILE_NOT_FOUND = "jsf.file.service.file.not.found";

	protected static final String MODELS = "models";
	protected static final String DAOS = "daos";
	protected static final String IMPL = "impl";
	protected static final String DAO_JAVA = "Dao.java";
	protected static final String POM = "pom";
	protected static final String TEMPLATES = "templates";

	public JSFFileService(JSFApp app) {
		this.app = app;
	}

	@Override
	public void run() {
		BufferedWriter writer = null;

		try {
			Files.createDirectories(getPath().getParent());
			writer = Files.newBufferedWriter(getPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
			Template template = cfg.getTemplate(getTemplateName());
			template.process(getRoot(), writer);
		} catch (IOException e) {
			throw new RuntimeException(ResourceLoader.INSTANCE.getMessage(FILE_NOT_FOUND, e.getMessage()), e);
		} catch (TemplateException e) {
			throw new RuntimeException(ResourceLoader.INSTANCE.getMessage(CREATE_FILE_ERROR, app.getName(), e.getMessage()), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(ResourceLoader.INSTANCE.getMessage(WRITER_ERROR, e.getMessage()), e);
				}
			}
		}
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("app", app);

		return root;
	}

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSFFileService other = (JSFFileService) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		return true;
	}
}
