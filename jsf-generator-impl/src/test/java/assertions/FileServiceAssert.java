package assertions;

import java.util.List;

import org.fest.assertions.api.AbstractAssert;

import services.FileService;

public class FileServiceAssert extends AbstractAssert<FileServiceAssert, List<FileService>> {
	public FileServiceAssert(List<FileService> actual) {
		super(actual, FileServiceAssert.class);
	}

	public static FileServiceAssert assertThatMy(List<FileService> actual) {
		return new FileServiceAssert(actual);
	}

	public FileServiceAssert containsEvery(List<? extends FileService> expectedServices) {
		Integer listSize = 0;

		isNotNull();

		if (expectedServices == null || expectedServices.isEmpty()) {
			throw new AssertionError("Do not use this method for an empty or null list of services, use doesNotContain instead.");
		}

		Class<? extends FileService> serviceClass = expectedServices.get(0).getClass();

		for (FileService f : actual) {
			if (f.getClass().equals(serviceClass)) {
				listSize++;
			}
		}

		if (listSize != expectedServices.size()) {
			throw new AssertionError("expected " + expectedServices.size() + " " + serviceClass.getSimpleName() + " but was " + listSize + ".");
		}

		return this;
	}
}
