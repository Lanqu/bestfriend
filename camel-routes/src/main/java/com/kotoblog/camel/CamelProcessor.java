package com.kotoblog.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class CamelProcessor implements Processor {

	@Override
	public void process(Exchange arg0) throws Exception {
		System.out.print("Ololo");
	}

}
