package com.kotoblog.persist.bind;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;

public class JndiBinder {

	@Autowired
	private JndiTemplate jndiTemplate;

	private final Map<String, Object> resources;

	public JndiBinder(Map<String, Object> resources) {
		this.resources = resources;
	}

	@PostConstruct
	public void bind() throws NamingException {
		for (Entry<String, Object> entry : this.resources.entrySet()) {
			this.jndiTemplate.bind(entry.getKey(), entry.getValue());
		}
	}

	public JndiTemplate getJndiTemplate() {
		return this.jndiTemplate;
	}

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}

}
