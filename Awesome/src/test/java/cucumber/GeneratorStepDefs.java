package cucumber;

import static java.nio.file.FileVisitResult.CONTINUE;
import static org.fest.assertions.api.Assertions.assertThat;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonIOException;
import gherkin.deps.com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import junit.framework.TestCase;
import models.App;
import models.impl.JSFApp;
import controllers.GeneratorController;
import controllers.impl.JSFGeneratorController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class GeneratorStepDefs extends TestCase {
	private App app;

	@Given("^that I have parsed \"([^\"]*)\"$")
	public void that_I_have_parsed(String mappingFile) throws Throwable {
	    Path mappingPath = Paths.get("src", "test", "resources", "mappedFiles", mappingFile);

	    assertThat(mappingPath.toFile()).exists();

	    try (BufferedReader reader = Files.newBufferedReader(mappingPath, Charset.defaultCharset())) {
	    	Gson gson = new GsonBuilder().create();
	    	app = gson.fromJson(reader, JSFApp.class);
	    } catch (JsonSyntaxException jse) {
	    	throw new RuntimeException("error parsing json: " + jse.getMessage(), jse);
	    } catch (JsonIOException jio) {
	    	throw new RuntimeException("I/O exception from reader: " + jio.getMessage(), jio);
	    }

	    // delete the generated folder if it exists
	    JSFApp jsfApp = (JSFApp) app;
	    Path sourceFolder = Paths.get(jsfApp.getName());

	    if (Files.exists(sourceFolder)) {
		    FileTreeEraser fileTreeEraser = new FileTreeEraser();
		    Files.walkFileTree(Paths.get(jsfApp.getName()), fileTreeEraser);
	    }
	}

	@When("^I generate the app$")
	public void I_generate_the_app() throws Throwable {
	    GeneratorController generator = new JSFGeneratorController();

	    generator.create(app);
	}

	@Then("^I should see the following project: \"([^\"]*)\"$")
	public void I_should_see_the_following_project(String sourceFolder) throws Throwable {
		Path expectedPath = Paths.get("src", "test", "resources", "expectedResults", sourceFolder);
		Path actualPath = Paths.get(sourceFolder);

		// step 1: checks if the generated project has the expected file tree.
		FileTreeComparator fileTreeComparator = new FileTreeComparator(expectedPath, actualPath);
		Files.walkFileTree(expectedPath, fileTreeComparator);

		// step 2: checks if the generated project has no other files other than the expected.
		fileTreeComparator = new FileTreeComparator(actualPath, expectedPath);
		Files.walkFileTree(actualPath, fileTreeComparator);
	}

	private class FileTreeComparator extends SimpleFileVisitor<Path> {
		private Path expectedPath;
		private Path actualPath;

		public FileTreeComparator(Path expectedPath, Path actualPath) {
			this.expectedPath = expectedPath;
			this.actualPath = actualPath;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path expectedDir, BasicFileAttributes atts) throws IOException {
			Path relativePath = expectedPath.relativize(expectedDir);
			Path actualDir = actualPath.resolve(relativePath);

			assertThat(actualDir.toFile()).exists();

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path expectedFile, BasicFileAttributes attrs) throws IOException {
			Path relativePath =  expectedPath.relativize(expectedFile);
			Path actualFile = actualPath.resolve(relativePath);

			assertThat(actualFile.toFile()).exists();

			String expectedFileContents = new String(Files.readAllBytes(expectedFile));
			String actualFileContents = new String(Files.readAllBytes(actualFile));

			assertThat(actualFileContents).isEqualTo(expectedFileContents);

			return CONTINUE;
		}

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
        	throw new RuntimeException("could not read path: " + exc.getMessage(), exc);
        }
	}

	private class FileTreeEraser extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		    Files.delete(file);

		    return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
		    if (e == null) {
		        Files.delete(dir);

		        return FileVisitResult.CONTINUE;
		    } else {
		        throw e;
		    }
		}
	}
}