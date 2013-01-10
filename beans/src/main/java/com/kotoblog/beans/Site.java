package com.kotoblog.beans;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Site {

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
	List<String> excluders;

	// Clear anchors?
	Boolean stripLinks;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentXpath() {
		return contentXpath;
	}

	public void setContentXpath(String xpath) {
		this.contentXpath = xpath;
	}

	public String getListerXpath() {
		return listerXpath;
	}

	public void setListerXpath(String lister) {
		this.listerXpath = lister;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setSearchXpath(String goButton) {
		this.searchXpath = goButton;
	}

	public String getSearchXpath() {
		return searchXpath;
	}

	public String getKeywordFieldXpath() {
		return keywordFieldXpath;
	}

	public void setKeywordFieldXpath(String keywordField) {
		this.keywordFieldXpath = keywordField;
	}

	public List<String> getExcluders() {

		if (excluders == null) {
			excluders = new LinkedList<String>();
		}

		return excluders;
	}

	public void setExcluders(List<String> excluders) {
		this.excluders = excluders;
	}

	public Boolean getStripLinks() {
		return stripLinks;
	}

	public void setStripLinks(Boolean stripLinks) {
		this.stripLinks = stripLinks;
	}

	public String getTitleXpath() {
		return titleXpath;
	}

	public void setTitleXpath(String title) {
		this.titleXpath = title;
	}

	public String getLinksXpath() {
		return linksXpath;
	}

	public void setLinksXpath(String linksXpath) {
		this.linksXpath = linksXpath;
	}

}
