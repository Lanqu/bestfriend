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

@Service
public class CamelParser implements Processor {

	@Autowired
	private EzineCmd ezine;

	@Override
	public void process(Exchange arg0) throws Exception {
		Site lastMessage = arg0.getIn().getBody(Site.class);

		System.out.println("MESSAGE RECEIVED: " + lastMessage.getUrl());
		try {
			this.ezine.setSite(lastMessage);
			this.ezine.execute();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CaptchaParserException e) {
			e.printStackTrace();
		}
	}

}
