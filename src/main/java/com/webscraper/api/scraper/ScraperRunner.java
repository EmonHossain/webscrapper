package com.webscraper.api.scraper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ScraperRunner implements CommandLineRunner {

	@Autowired
	private ProxyScraper proxyScraper;

	@Override
	public void run(String... args) throws Exception {

		while (true) {

			CompletableFuture<List<Proxy>> proxy0 = proxyScraper.scrapFromFreeProxyListOne();
			CompletableFuture<List<Proxy>> proxy1 = proxyScraper.scrapFromfreeproxylistsThree();
			CompletableFuture<List<Proxy>> proxy2 = proxyScraper.scrapFromProxynova();
			CompletableFuture<List<Proxy>> proxy3 = proxyScraper.scrapFromNetzwelt();
			CompletableFuture<List<Proxy>> proxy4 = proxyScraper.scrapFromPremproxy();

			CompletableFuture.allOf(proxy0, proxy1, proxy2, proxy3, proxy4).join();
			
			Thread.sleep(10*60*1000L);			
		}
	}

}
