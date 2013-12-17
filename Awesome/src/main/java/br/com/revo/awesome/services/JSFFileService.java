package br.com.revo.awesome.services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import br.com.revo.awesome.enums.FreeMarkerConfiguration;
import br.com.revo.awesome.models.impl.JSFApp;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class JSFFileService implements FileService {
	protected JSFApp app;
	private Configuration cfg = FreeMarkerConfiguration.INSTANCE.getConfiguration();

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
			throw new RuntimeException("Could not find or create file: " + e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException("Error creating file " + app.getName() + ": " + e.getMessage(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException("Could not close writer: " + e.getMessage(), e);
				}
			}
		}
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
