package com.kotoblog.camel;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class CamelSender {

	@Produce(uri = "jms:job")
	private ProducerTemplate producer;
	
	public void send(String msg) {
		producer.sendBody("Trololololo");
	}

}
