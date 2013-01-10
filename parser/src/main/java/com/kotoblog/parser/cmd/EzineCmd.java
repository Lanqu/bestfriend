package com.kotoblog.parser.cmd;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.kotoblog.beans.Site;
import com.kotoblog.parser.exception.CaptchaParserException;

@Scope("prototype")
@Service
public class EzineCmd implements Command {
	
	private Site site;
	private HtmlPage page;

	@Autowired
	private WebClient client;

	@SuppressWarnings("unchecked")
	@Override
	public void execute() throws FailingHttpStatusCodeException, MalformedURLException, IOException, CaptchaParserException {
		
		// load page
		page = client.getPage(site.getUrl());
		
		// find keyword field and enter keyword
		HtmlElement keywordField = (HtmlElement) page.getFirstByXPath(site.getKeywordFieldXpath());
		keywordField.type(site.getKeyword());
		
		// find search button
		HtmlButton searchButton = (HtmlButton) page.getFirstByXPath(site.getSearchXpath());
		
		// click button
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		page = searchButton.click();
		
		// check if captcha appears
		if (page.asXml().contains("recaptcha_area")) {
			throw new CaptchaParserException();
		}
		
		// find link for listing results
		List<HtmlAnchor> linkslist = new ArrayList<HtmlAnchor>();
		HtmlAnchor next = (HtmlAnchor) page.getFirstByXPath(site.getListerXpath());
		
		// collect links
		while (next != null) {
			linkslist.addAll((Collection<? extends HtmlAnchor>) page.getByXPath(site.getLinksXpath()));
			
			page = next.click();
			next = (HtmlAnchor) page.getByXPath(site.getListerXpath());
		}
		
		// TODO start process of collecting tests
		for (HtmlAnchor link: linkslist) {
			page = link.click();
		}
		
		// TODO add saving to hibernate hsqldb of links
		// TODO add saving to hibernate hsqldb of collected content
		// TODO add queue for rewriting content
	}
	
	// TODO 1 queue - collect links
	// TODO 2 queue - collect content
	// TODO 3 queue - rewrite content
	// TODO 4 queue - save to DB

	public void setClient(WebClient client) {
		this.client = client;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
