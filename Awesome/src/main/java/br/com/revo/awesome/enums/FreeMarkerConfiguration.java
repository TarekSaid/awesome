package br.com.revo.awesome.enums;

import java.io.IOException;
import java.nio.file.Paths;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public enum FreeMarkerConfiguration {
	INSTANCE;

	private Configuration cfg = new Configuration();

	private FreeMarkerConfiguration() {
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		try {
			cfg.setDirectoryForTemplateLoading(Paths.get("src", "main", "resources", "templates").toFile());
		} catch (IOException e) {
			throw new RuntimeException("could not load template files: " + e.getMessage(), e);
		}

		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public Configuration getConfiguration() {
		return cfg;
	}
}