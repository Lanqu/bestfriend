package com.kotoblog.camel;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.kotoblog.beans.Site;
import com.kotoblog.parser.cmd.EzineCmd;
import com.kotoblog.parser.exception.CaptchaParserException;
import com.kotoblog.persist.service.IEntityService;

@Service
public class CamelProcessor implements Processor {

	@Autowired
	private EzineCmd ezine;

	@Autowired
	private IEntityService entityService;

	@Autowired
	private Site site;

	@Override
	public void process(Exchange arg0) throws Exception {
		String lastMessage = arg0.getIn().getBody(String.class);

		System.out.println("MESSAGE RECEIVED: " + lastMessage);
		try {
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

			this.entityService.saveEntity(this.site);

			this.ezine.setSite(this.site);
			this.ezine.execute();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CaptchaParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
