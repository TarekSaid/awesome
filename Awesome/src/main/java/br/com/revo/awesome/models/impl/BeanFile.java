package br.com.revo.awesome.models.impl;

import br.com.revo.awesome.enums.ScopeEnum;

public class BeanFile {
	private String name;
	private ScopeEnum scope;

	public BeanFile(String name, ScopeEnum scope) {
		this.name = name;
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScopeEnum getScope() {
		return scope;
	}

	public void setScope(ScopeEnum scope) {
		this.scope = scope;
	}
}
