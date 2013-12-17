package br.com.revo.awesome.services;

import java.nio.file.Path;
import java.util.Map;

public interface FileService extends Runnable {
	public Path getPath();
	public String getTemplateName();
	public Map<String, Object> getRoot();
}
