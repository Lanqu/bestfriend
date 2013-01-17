package com.kotoblog.persist.bind;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataSourceBinder {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jndi.dts.name}")
	private String jndiName;

	@Autowired
	private JndiTemplate jndiTemplate;

	@PostConstruct
	public void bind() throws NamingException {
		InitialContext ic = new InitialContext();

		Reference ref = new Reference("javax.sql.DataSource", "org.apache.commons.dbcp.BasicDataSourceFactory", null);
		ref.add(new StringRefAddr("driverClassName", this.driverClassName));
		ref.add(new StringRefAddr("url", this.url));
		ref.add(new StringRefAddr("username", this.username));
		ref.add(new StringRefAddr("password", this.password));
		ic.rebind(this.jndiName, ref);
	}

	@Bean
	public DataSource dataSource() throws NamingException {
		return this.jndiTemplate.lookup(this.jndiName, DataSource.class);
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJndiName() {
		return this.jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public JndiTemplate getJndiTemplate() {
		return this.jndiTemplate;
	}

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}

}
