package com.rubypaper.shopping;

import org.springframework.context.support.GenericXmlApplicationContext;

public class RubyShoppingClient {
	public static void main(String[] args) {
		String configuration = "classpath:spring/business-layer.xml";
		GenericXmlApplicationContext container = new GenericXmlApplicationContext(configuration);
		
		container.close();
	}
}
