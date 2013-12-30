package br.com.revo.awesome.models.files.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.revo.awesome.enums.ScopeEnum;
import br.com.revo.awesome.models.files.FileNameRestriction;

public class BeanFile extends FileNameRestriction {
	private ScopeEnum scope;
	private List<BeanField> fields = new ArrayList<>();

	public ScopeEnum getScope() {
		return scope;
	}

	public void setScope(ScopeEnum scope) {
		this.scope = scope;
	}

	public List<BeanField> getFields() {
		return fields;
	}

	public void setFields(List<BeanField> fields) {
		this.fields = fields;
	}
}
