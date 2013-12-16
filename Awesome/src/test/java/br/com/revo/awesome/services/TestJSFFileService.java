package br.com.revo.awesome.services;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestJSFFileService extends TestCase {
	@Mock JSFFileService fileService;

	@Test
	public void runShouldDoSomething() {
		fileService.run();

		Mockito.verify(fileService).run();
	}

	@After
	public void destroyFileService() {
		fileService = null;
	}
}
