package properties.impl;

import properties.AbstractResourceMap;

public class ExceptionsResourceMap extends AbstractResourceMap {
	@Override
	public void populateProperties() {
		properties.put("controller.executor.timeout", "Could not execute {0} tasks before 60 seconds.");
		properties.put("controller.executor.interrupted", "Could not execute {0} tasks: {1}");
		properties.put("freemarker.cfg.template.not.found", "Could not load template files: {0}");
		properties.put("jsf.file.service.file.not.found", "Could not find or create file: {0}");
		properties.put("jsf.file.service.create.file.error", "Error creating file {0}: {1}");
		properties.put("jsf.file.service.writer.error", "Could not close writer: {0}");
	}
}
