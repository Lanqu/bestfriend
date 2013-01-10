package com.kotoblog.bestfriend.hornetq.listeners;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.kotoblog.beans.Site;
import com.kotoblog.parser.cmd.EzineCmd;
import com.kotoblog.parser.exception.CaptchaParserException;

public class ExampleListener implements MessageListener {
	public static String lastMessage = null;

	@Autowired
	private EzineCmd ezine;

	@Autowired
	private Site site;

	public void onMessage(Message message) {
		try {
			lastMessage = ((TextMessage) message).getText();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		System.out.println("MESSAGE RECEIVED: " + lastMessage);
		try {
			site.setKeyword("hdd recovering");
			site.setKeywordFieldXpath("//*[@id='internal-search']//*[@name='q']");
			
			site.setUrl("http://ezinearticles.com");
			
			site.setContentXpath("//*[@id=\"article-contentXpath\"]");
			site.setSearchXpath("//*[@id='internal-search']//button");
			site.setTitleXpath("//*[@id='article-titleXpath']/h1");
			
			site.setListerXpath("//*[@id='search-results']//a[@class='next']");
			site.setLinksXpath("//*[@id='search-results']//h3/a");
			
			site.getExcluders().add("//*[@id=\"article-titleXpath\"]/p");
			site.getExcluders().add("//*[@id=\"article-body\"]/p");

			ezine.setSite(site);
			ezine.execute();
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