package com.webscraper.api.scraper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapperService {
	
	@Autowired
	private ProxyRepository repo;
	
	@Autowired
	private ProxyScraper proxyScraper;
	
	public List<Proxy> getAllProxy() {
		return repo.findAll();
	}
	
	public List<Proxy> scrapSites() {
		
		CompletableFuture<List<Proxy>> proxy0 = proxyScraper.scrapFromFreeProxyListOne();
		CompletableFuture<List<Proxy>> proxy1 = proxyScraper.scrapFromfreeproxylistsThree();
		CompletableFuture<List<Proxy>> proxy2 = proxyScraper.scrapFromProxynova();
		CompletableFuture<List<Proxy>> proxy3 = proxyScraper.scrapFromNetzwelt();
		CompletableFuture<List<Proxy>> proxy4 = proxyScraper.scrapFromPremproxy();

		CompletableFuture.allOf(proxy0, proxy1, proxy2, proxy3, proxy4).join();
		
		return getAllProxy();
	}
	
}
