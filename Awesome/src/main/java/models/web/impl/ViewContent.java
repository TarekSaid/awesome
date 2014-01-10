package models.web.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ViewContent {
	private String name;
	private String value;
	private Map<String, String> properties = new LinkedHashMap<>();
	private List<ViewContent> contents = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<ViewContent> getContents() {
		return contents;
	}

	public void setContents(List<ViewContent> contents) {
		this.contents = contents;
	}

	public String printContents(String indentation) {
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		String quotes = "\"";

		sb.append(indentation).append("<").append(name);

		for (Map.Entry<String, String> property : properties.entrySet()) {
			sb.append(" ").append(property.getKey()).append("=").append(quotes)
					.append(property.getValue()).append(quotes);
		}

		if (value == null && contents.isEmpty()) {
			sb.append(" />");
		} else {
			sb.append(">");

			if (value != null) {
				sb.append(value);
			}

			if (!contents.isEmpty()) {
				for (ViewContent content : contents) {
					sb.append(newLine).append(content.printContents(indentation + "  "));
				}

				sb.append(newLine).append(indentation);
			}

			sb.append("</").append(name).append(">");
		}

		return sb.toString();
	}
}
