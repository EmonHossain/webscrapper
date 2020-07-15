package com.webscraper.api.scraper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.webscraper.api.utils.ConfigDrivers;

@Service
public class ProxyScraper {

	@Value("${proxy.urls}")
	private List<String> URLs;
	
	@Autowired
	private ProxyRepository repo;

	@Autowired
	private ConfigDrivers driverConfig;

	@Async
	public CompletableFuture<List<Proxy>> scrapFromFreeProxyListOne() {
		WebDriver driver = driverConfig.getWebDriver();

		List<Proxy> proxyList = new ArrayList<Proxy>();

		try {
			driver.get(URLs.get(0));
			Document html = Jsoup.parse(driver.getPageSource());
			driver.quit();
			html.select("table").get(0).select("tr").parallelStream().forEach(tr -> {
				Elements data = tr.select("td");
				if (data.size() != 0) {
					Proxy p = new Proxy();
					p.setIp(data.get(0).text());
					p.setPort(Integer.parseInt(data.get(1).text()));
					p.setFirstFound(new Date());
					p.setLastFound(new Date());
					p.setTestDate(new Date());
					p.setTestUrlDate(new Date());
					proxyList.add(p);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		System.out.println(URLs.get(0) + " : " + proxyList.size());
		repo.saveAllProxy(proxyList);
		return CompletableFuture.completedFuture(proxyList);
	}

	@Async
	public CompletableFuture<List<Proxy>> scrapFromfreeproxylistsThree() {
		WebDriver driver = driverConfig.getWebDriver();

		List<Proxy> proxyList = new ArrayList<Proxy>();

		try {
			driver.get(URLs.get(1));

			driver.findElement(By.className("DataGrid")).findElements(By.tagName("tr")).stream().skip(1)
					.collect(Collectors.toList()).parallelStream().forEach(tr -> {
						List<WebElement> we = tr.findElements(By.tagName("td"));

						if (we.size() == 10) {
							String ip = we.get(0).findElement(By.tagName("a")).getText();
							String port = we.get(1).getText();

							Proxy p = new Proxy();
							p.setIp(ip);
							p.setPort(Integer.parseInt(port));
							p.setFirstFound(new Date());
							p.setLastFound(new Date());
							p.setTestDate(new Date());
							p.setTestUrlDate(new Date());
							proxyList.add(p);
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		System.out.println(URLs.get(1) + " : " + proxyList.size());
		repo.saveAllProxy(proxyList);
		return CompletableFuture.completedFuture(proxyList);
	}

	@Async
	public CompletableFuture<List<Proxy>> scrapFromProxynova() {
		WebDriver driver = driverConfig.getWebDriver();

		List<Proxy> proxyList = new ArrayList<Proxy>();

		try {

			driver.get(URLs.get(2));

			driver.findElement(By.id("tbl_proxy_list")).findElements(By.tagName("tbody")).get(0)
					.findElements(By.tagName("tr")).parallelStream().forEach(tr -> {
						List<WebElement> we = tr.findElements(By.tagName("td"));

						if (we.size() == 8) {
							String ip = we.get(0).findElement(By.tagName("abbr")).getText();
							String port = we.get(1).getText();

							Proxy p = new Proxy();
							p.setIp(ip);
							p.setPort(Integer.parseInt(port));
							p.setFirstFound(new Date());
							p.setLastFound(new Date());
							p.setTestDate(new Date());
							p.setTestUrlDate(new Date());
							proxyList.add(p);
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		System.out.println(URLs.get(2) + " : " + proxyList.size());
		repo.saveAllProxy(proxyList);
		return CompletableFuture.completedFuture(proxyList);
	}

	@Async
	public CompletableFuture<List<Proxy>> scrapFromNetzwelt() {
		WebDriver driver = driverConfig.getWebDriver();

		List<Proxy> proxyList = new ArrayList<Proxy>();

		try {

			driver.get(URLs.get(3));

			driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).parallelStream().forEach(tr -> {
				List<WebElement> we = tr.findElements(By.tagName("td"));

				if (we.size() == 6) {
					String ip = we.get(0).findElement(By.tagName("a")).getAttribute("title");
					String port = we.get(1).getText();

					Proxy p = new Proxy();
					p.setIp(ip);
					p.setPort(Integer.parseInt(port));
					p.setFirstFound(new Date());
					p.setLastFound(new Date());
					p.setTestDate(new Date());
					p.setTestUrlDate(new Date());
					proxyList.add(p);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		System.out.println(URLs.get(3) + " : " + proxyList.size());
		repo.saveAllProxy(proxyList);
		return CompletableFuture.completedFuture(proxyList);
	}

	@Async
	public CompletableFuture<List<Proxy>> scrapFromPremproxy() {
		WebDriver driver = driverConfig.getWebDriver();

		List<Proxy> proxyList = new ArrayList<Proxy>();

		try {

			driver.get(URLs.get(4));

			driver.findElement(By.id("proxylistt")).findElements(By.className("anon")).parallelStream().forEach(tr -> {
				List<WebElement> we = tr.findElements(By.tagName("td"));

				if (we.size() == 6) {
					String[] ipAndPort = we.get(0).getText().split(":");

					Proxy p = new Proxy();
					p.setIp(ipAndPort[0]);
					p.setPort(Integer.parseInt(ipAndPort[1]));
					p.setFirstFound(new Date());
					p.setLastFound(new Date());
					p.setTestDate(new Date());
					p.setTestUrlDate(new Date());
					proxyList.add(p);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		System.out.println(URLs.get(4) + " : " + proxyList.size());
		repo.saveAllProxy(proxyList);
		return CompletableFuture.completedFuture(proxyList);
	}

}
