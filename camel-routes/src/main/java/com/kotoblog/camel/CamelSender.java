package com.kotoblog.camel;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kotoblog.beans.Site;

@Service
public class CamelSender {

	@Value("jms:queue:${jms.save}")
	private String jmsSave;

	@Value("jms:queue:${jms.parser}")
	private String jmsParser;

	@Produce
	private ProducerTemplate producer;

	public void sendToSave(Site msg) {
		this.producer.sendBody(this.jmsSave, msg);
	}

	public void sendToParser(Site msg) {
		this.producer.sendBody(this.jmsParser, msg);
	}


}
