package com.kotoblog.bestfriend.pages;

import java.util.Date;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

import com.kotoblog.beans.Site;
import com.kotoblog.camel.CamelSender;

/**
 * Start page of application bestfriend.
 */
public class Index {
	@Property
	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	private String tapestryVersion;

	@InjectComponent
	private Zone zone;

	@Persist
	@Property
	private int clickCount;

	@Inject
	private AlertManager alertManager;

	@Inject
	private CamelSender sender;

	@Inject
	private Site site;

	public Date getCurrentTime() {
		return new Date();
	}

	void onActionFromGo() {
		this.site.setKeyword("hdd recovering");
		this.site.setKeywordFieldXpath("//*[@id='internal-search']//*[@name='q']");

		this.site.setUrl("http://ezinearticles.com");

		this.site.setContentXpath("//*[@id=\"article-contentXpath\"]");
		this.site.setSearchXpath("//*[@id='internal-search']//button");
		this.site.setTitleXpath("//*[@id='article-titleXpath']/h1");

		this.site.setListerXpath("//*[@id='search-results']//a[@class='next']");
		this.site.setLinksXpath("//*[@id='search-results']//h3/a");

		this.site.getExcluders().add("//*[@id=\"article-titleXpath\"]/p");
		this.site.getExcluders().add("//*[@id=\"article-body\"]/p");

		this.sender.sendToSave(this.site);
	}

	void onActionFromIncrement() {
		this.alertManager.info("Increment clicked");

		this.clickCount++;
	}

	Object onActionFromIncrementAjax() {
		this.clickCount++;

		this.alertManager.info("Increment (via Ajax) clicked");

		return this.zone;
	}

	public void setSender(final CamelSender sender) {
		this.sender = sender;
	}
}
