package properties;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class AbstractResourceMap extends ResourceBundle implements ResourceMap {
	protected Map<String, Object> properties = new HashMap<>();

	public AbstractResourceMap() {
		populateProperties();
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(properties.keySet());
	}

	@Override
	protected Object handleGetObject(String key) {
		return properties.get(key);
	}
}
