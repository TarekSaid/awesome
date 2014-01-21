import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import services.impl.TestAbstractDaoFileService;
import services.impl.TestBeanFileService;
import services.impl.TestDaoFileService;
import services.impl.TestDaoImplFileService;
import services.impl.TestDataSourceFileService;
import services.impl.TestIdFileService;
import services.impl.TestModelFileService;
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
	TestViewFileService.class,
	TestModelFileService.class,
	TestIdFileService.class,
	TestDaoFileService.class,
	TestDaoImplFileService.class,
	TestAbstractDaoFileService.class,
	TestDataSourceFileService.class
})
public class GeneratorTestSuite {
}
