package com.webscraper.api.scraper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProxyRepository {

	@Autowired
	private EntityManager manager;
	
	public List<Proxy> findAll() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Proxy> query = builder.createQuery(Proxy.class);
		Root<Proxy> root = query.from(Proxy.class);
		query.select(root);
		List<Proxy> proxies = manager.createQuery(query).getResultList();
		
		return proxies;
	}
	
	@Transactional
	public void saveAllProxy(List<Proxy> proxies) {
		if(!proxies.isEmpty()) {
			for(Proxy p : proxies) {
				System.out.println("IP : "+p.getIp());
				Proxy prx = manager.find(Proxy.class, p.getIp());
				if(prx != null) {
					prx.setLastFound(new Date());
					prx.setTestDate(new Date());
				}else {
					manager.persist(p);
				}
			}
		}
	}
	
	public void saveProxy(Proxy proxy) {
		manager.persist(proxy);
	}
	
	public void updateProxy(String ip) {
		Proxy proxy = manager.find(Proxy.class, ip);
		proxy.setTestDate(new Date());
		manager.flush();
	}
	
	public void remove(String ip) {
		Proxy p = manager.getReference(Proxy.class, ip);
		manager.remove(p);
	}
	
}
