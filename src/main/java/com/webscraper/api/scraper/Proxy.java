package com.webscraper.api.scraper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Proxy {

	public Proxy(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	private String ip;
	private boolean status;
	private String location;
	private int port;
	
}
