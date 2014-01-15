import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import services.impl.TestBeanFileService;
import services.impl.TestPomFileService;
import services.impl.TestViewFileService;
import services.impl.TestWebFileService;
import controllers.impl.TestGeneratorControllerImpl;
import factories.impl.TestJSFServiceFactory;

@RunWith(Suite.class)
@SuiteClasses({
	TestGeneratorControllerImpl.class,
	TestJSFServiceFactory.class,
	TestPomFileService.class,
	TestWebFileService.class,
	TestBeanFileService.class,
	TestViewFileService.class
})
public class GeneratorTestSuite {
}
