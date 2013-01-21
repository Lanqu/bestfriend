package com.kotoblog.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.kotoblog.beans.Site;
import com.kotoblog.persist.service.IEntityService;

@Service
public class CamelSave implements Processor {

	@Autowired
	private IEntityService entityService;

	@Override
	public void process(Exchange exchange) throws Exception {

		Site site = exchange.getIn().getBody(Site.class);

		System.out.println("MESSAGE RECEIVED: " + site.getUrl());
		try {
			this.entityService.saveEntity(site);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		}

	}

}
