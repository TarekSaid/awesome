package models.web.impl;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import models.web.impl.ViewContent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestViewContent extends TestCase {
	private ViewContent content;
	private String newLine = System.getProperty("line.separator");

	@Before
	public void prepareViewContent() {
		content = new ViewContent();
	}

	@Test
	public void getContentsShouldOutputName() {
		content.setName("test");

		compareContents("<test />");
	}

	@Test
	public void getContentsShouldOutputProperties() {
		content.setName("html");
		content.getProperties().put("test", "true");
		content.getProperties().put("name", "awesome-demo");

		compareContents("<html test=\"true\" name=\"awesome-demo\" />");
	}

	@Test
	public void getContentsShouldOutputValue() {
		content.setName("html");
		content.setValue("test");

		String expectedContents = "<html>test</html>";
		String actualContents = content.printContents("");

		assertEquals(expectedContents, actualContents);
	}

	@Test
	public void getContentsShouldOutputSubContents() {
		String expectedContents;
		List<ViewContent> subContents = new LinkedList<>();

		ViewContent text = new ViewContent();
		text.setName("h:text");
		text.getProperties().put("value", "test");

		subContents.add(text);

		ViewContent button = new ViewContent();
		button.setName("h:command");
		button.getProperties().put("value", "ok");
		button.getProperties().put("action", "testResult");

		subContents.add(button);

		content.setName("h:form");
		content.setContents(subContents);

		String indentation = "  ";
		expectedContents = "<h:form>" + newLine +
			indentation + "<h:text value=\"test\" />" + newLine +
			indentation + "<h:command value=\"ok\" action=\"testResult\" />" + newLine +
		"</h:form>";
		compareContents(expectedContents);
	}
	
	@Test
	public void getContentsShouldOutputNestedContents() {
		List<ViewContent> contents = new LinkedList<>();

		ViewContent nest = new ViewContent();
		nest.setName("first-nested");

		contents.add(nest);

		ViewContent lastNest = new ViewContent();
		lastNest.setName("last-nest");
		lastNest.setValue("hello");

		nest = new ViewContent();
		nest.setName("second-nested");
		nest.getContents().add(lastNest);

		contents.add(nest);

		ViewContent subContent = new ViewContent();
		subContent.setName("sub");
		subContent.setValue("subContentTest");
		subContent.setContents(contents);

		ViewContent test = new ViewContent();
		test.setName("test");
		test.getProperties().put("value", "ok");
		test.getContents().add(subContent);

		content.setName("html");
		content.getContents().add(test);

		

		String indentation = "  ";
		String expectedContents =
		"<html>" + newLine +
			indentation + "<test value=\"ok\">" + newLine +
				indentation + indentation + "<sub>subContentTest" + newLine +
					indentation + indentation + indentation + "<first-nested />" + newLine +
					indentation + indentation + indentation + "<second-nested>" + newLine +
						indentation + indentation + indentation + indentation + "<last-nest>hello</last-nest>" + newLine +
					indentation + indentation + indentation + "</second-nested>" + newLine +
				indentation + indentation + "</sub>" + newLine +
			indentation + "</test>" + newLine +
		"</html>";
		compareContents(expectedContents);
	}

	private void compareContents(String expectedContents) {
		String actualContents = content.printContents("");

		assertEquals("expected: " + expectedContents + ".\n actual: " + actualContents, expectedContents, actualContents);
	}

	@After
	public void destroyViewContent() {
		content = null;
		newLine = null;
	}
}
