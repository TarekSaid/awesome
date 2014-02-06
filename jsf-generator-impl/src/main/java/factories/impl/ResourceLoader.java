package factories.impl;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum ResourceLoader {
	EXCEPTIONS("properties.Exceptions");

	private ResourceBundle bundle;

	private ResourceLoader(String bundleName) {
		this.bundle = ResourceBundle.getBundle(bundleName);
	}

	public String getMessage(String key, Object... params) {
		return MessageFormat.format(bundle.getString(key), params);
	}
}
