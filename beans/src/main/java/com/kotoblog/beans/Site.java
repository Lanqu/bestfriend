package com.kotoblog.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Site implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7923961601994313645L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// site url
	String url;

	// contentXpath for pulling out text
	String contentXpath;

	// button to get press after keyword enter
	String searchXpath;

	// next button or link
	String listerXpath;

	// xpath for selecting links on the search page
	String linksXpath;

	// campaign keyword
	String keyword;

	// keyword field contentXpath
	String keywordFieldXpath;

	// titleXpath of article
	String titleXpath;

	// List of excluders that can be removed by contentXpath
	@ElementCollection(fetch = FetchType.EAGER)
	List<String> excluders;

	// Clear anchors?
	Boolean stripLinks = true;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentXpath() {
		return this.contentXpath;
	}

	public void setContentXpath(String xpath) {
		this.contentXpath = xpath;
	}

	public String getListerXpath() {
		return this.listerXpath;
	}

	public void setListerXpath(String lister) {
		this.listerXpath = lister;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setSearchXpath(String goButton) {
		this.searchXpath = goButton;
	}

	public String getSearchXpath() {
		return this.searchXpath;
	}

	public String getKeywordFieldXpath() {
		return this.keywordFieldXpath;
	}

	public void setKeywordFieldXpath(String keywordField) {
		this.keywordFieldXpath = keywordField;
	}

	public List<String> getExcluders() {

		if (this.excluders == null) {
			this.excluders = new LinkedList<String>();
		}

		return this.excluders;
	}

	public void setExcluders(List<String> excluders) {
		this.excluders = excluders;
	}

	public Boolean getStripLinks() {
		return this.stripLinks;
	}

	public void setStripLinks(Boolean stripLinks) {
		this.stripLinks = stripLinks;
	}

	public String getTitleXpath() {
		return this.titleXpath;
	}

	public void setTitleXpath(String title) {
		this.titleXpath = title;
	}

	public String getLinksXpath() {
		return this.linksXpath;
	}

	public void setLinksXpath(String linksXpath) {
		this.linksXpath = linksXpath;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
