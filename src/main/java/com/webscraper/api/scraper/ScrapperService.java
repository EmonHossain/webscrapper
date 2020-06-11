package com.webscraper.api.scraper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.webscraper.api.utils.ConfigDrivers;

@Service
public class ScrapperService {

	@Value("${proxy.urls}")
	private List<String> urls;
	
	@Autowired
	private ConfigDrivers driverConfig;
	
	public List<Proxy> collectproxyList(){
		WebDriver driver =driverConfig.getWebDriver();
		
		List<Proxy> proxyList= new ArrayList<Proxy>();
		
		for (String url : urls) {
			driver.get(url);
			long start, end;
			
			/*start = System.currentTimeMillis();
			
			driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).parallelStream()
													.forEach(tr -> {
														
														List<WebElement> els = tr.findElements(By.tagName("td"));
														proxyList.add(new Proxy(els.get(0).getText(), Integer.parseInt(els.get(1).getText())));
													});
			end = System.currentTimeMillis();
			System.out.println("selenium : "+(end-start));
*/
			Document html = Jsoup.parse(driver.getPageSource());
			driver.quit();
			start = System.currentTimeMillis();
			
			html.select("table").get(0).select("tr").parallelStream().forEach(tr -> {
				Elements data = tr.select("td");
				if(data.size() != 0)
					proxyList.add(new Proxy(data.get(0).text(), Integer.parseInt(data.get(1).text())));
			});
			
			end = System.currentTimeMillis();
			System.out.println("Jsoup : "+(end-start));
		}
		
		
		
		return proxyList;
	}
	
	
}
