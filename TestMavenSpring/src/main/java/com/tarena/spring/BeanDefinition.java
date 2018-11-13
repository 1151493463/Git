package com.tarena.spring;

import java.util.HashMap;
import java.util.Map;

public class BeanDefinition {
	private String id;
	private String clazz;
	private Map<String,PropertyDefinition> properties = new HashMap<String,PropertyDefinition>();
	
	public Map<String, PropertyDefinition> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, PropertyDefinition> properties) {
		this.properties = properties;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	
}
