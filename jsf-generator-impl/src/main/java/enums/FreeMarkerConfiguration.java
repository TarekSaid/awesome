package enums;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import factories.impl.ResourceLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public enum FreeMarkerConfiguration {
	INSTANCE;

	private static final String TEMPLATE_ERROR = "freemarker.cfg.template.not.found";
	private Configuration cfg = new Configuration();

	private FreeMarkerConfiguration() {
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
		try {
			cfg.setDirectoryForTemplateLoading(Paths.get("src", "main", "resources", "templates").toFile());
		} catch (IOException e) {
			throw new RuntimeException(ResourceLoader.EXCEPTIONS.getMessage(TEMPLATE_ERROR, e.getMessage()), e);
		}

		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public Configuration getConfiguration() {
		return cfg;
	}
}