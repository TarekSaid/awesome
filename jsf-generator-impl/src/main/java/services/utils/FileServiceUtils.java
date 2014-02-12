package services.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum FileServiceUtils {
	INSTANCE;

	private static final String MAIN = "main";
	private static final String SRC = "src";

	private static final Path JAVA_PATH = Paths.get(SRC, MAIN, "java");
	private static final Path WEBAPP_PATH = Paths.get(SRC, MAIN, "webapp");
	private static final Path RESOURCES_PATH = Paths.get(SRC, MAIN, "resources");

	public Path getJavaPath(String name) {
		return Paths.get(name).resolve(JAVA_PATH);
	}

	public Path getWebappPath(String name) {
		return Paths.get(name).resolve(WEBAPP_PATH);
	}

	public Path getResourcesPath(String name) {
		return Paths.get(name).resolve(RESOURCES_PATH);
	}
}
