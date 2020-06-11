package com.webscraper.api.scraper;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class ScraperController {
	
	@Autowired
	private ScrapperService scrapperService;
		
	@GetMapping("/scrap")
	public ResponseEntity<List<Proxy>> CollectInfoFormSite() {
		//return new ResponseEntity<List<Proxy>>(scrapperService.collectproxyList(),HttpStatus.OK);
		return new ResponseEntity<List<Proxy>>(Collections.emptyList(),HttpStatus.OK);
	}
}
