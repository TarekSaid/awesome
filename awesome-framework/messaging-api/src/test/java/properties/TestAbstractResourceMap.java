package properties;

import static org.fest.assertions.api.Assertions.assertThat;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestAbstractResourceMap extends TestCase {
	private class TestResourceMap extends AbstractResourceMap {
		@Override
		public void populateProperties() {
			this.properties.put("test", "value1");
			this.properties.put("test2", "value2");
			this.properties.put("test3", Boolean.TRUE);
		}
	}

	private AbstractResourceMap resourceMap;

	@Before
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

	@After
	public void destroyResourceMap() {
		resourceMap = null;
	}
}
