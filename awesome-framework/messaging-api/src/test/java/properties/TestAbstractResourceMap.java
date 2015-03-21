package properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class TestAbstractResourceMap {
	private class TestResourceMap extends AbstractResourceMap {
		@Override
		public void populateProperties() {
			this.properties.put("test", "value1");
			this.properties.put("test2", "value2");
			this.properties.put("test3", Boolean.TRUE);
		}
	}

	private AbstractResourceMap resourceMap;

	@BeforeTest
	public void prepareResourceMap() {
		resourceMap = new TestResourceMap();
	}

	@Test
	public void getStringShouldReturnCorrectValue() {
		assertThat(resourceMap.getString("test")).isEqualTo("value1");
		assertThat(resourceMap.getString("test2")).isEqualTo("value2");
	}

	@Test
	public void getObjectShouldReturnCorrectValue() {
		assertThat(resourceMap.getObject("test3")).isEqualTo(true);
	}

	@AfterTest
	public void destroyResourceMap() {
		resourceMap = null;
	}
}
