package br.com.revo.awesome.cucumber;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonIOException;
import gherkin.deps.com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import junit.framework.TestCase;
import br.com.revo.awesome.controllers.GeneratorController;
import br.com.revo.awesome.controllers.impl.JSFGeneratorController;
import br.com.revo.awesome.models.App;
import br.com.revo.awesome.models.impl.JSFApp;
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

	    assertTrue("Mapped file " + mappingPath.getFileName() + " not found.", Files.exists(mappingPath));

	    try (BufferedReader reader = Files.newBufferedReader(mappingPath, Charset.defaultCharset())) {
	    	Gson gson = new GsonBuilder().create();
	    	app = gson.fromJson(reader, JSFApp.class);
	    } catch (JsonSyntaxException jse) {
	    	throw new RuntimeException("error parsing json: " + jse.getMessage(), jse);
	    } catch (JsonIOException jio) {
	    	throw new RuntimeException("I/O exception from reader: " + jio.getMessage(), jio);
	    }
	}

	@When("^I generate the app$")
	public void I_generate_the_app() throws Throwable {
	    GeneratorController generator = new JSFGeneratorController();

	    generator.create(app);
	}

	@Then("^I should see the following files: \"([^\"]*)\" from the source folder \"([^\"]*)\"$")
	public void I_should_see_the_following_files_from_the_source_folder(List<String> files, String sourceFolder) throws Throwable {
	    for (String filePath: files) {
	    	Path expectedFile = Paths.get("src", "test", "resources", "expectedResults", sourceFolder, filePath);
	    	Path receivedFile = Paths.get(sourceFolder, filePath);

	    	assertTrue("File " + expectedFile.getFileName() + " not found.", Files.exists(receivedFile));

			String actualFileContents = new String(Files.readAllBytes(receivedFile));
			String expectedFileContents = new String(Files.readAllBytes(expectedFile));
	    	assertEquals("The file created does not match the expected file.", expectedFileContents, actualFileContents);
	    }
	}
}
