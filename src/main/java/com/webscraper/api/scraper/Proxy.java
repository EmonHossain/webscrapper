package com.webscraper.api.scraper;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proxies")
@Entity
@EqualsAndHashCode
public class Proxy {

	@Id
	@Column(nullable = false, unique = true,length=15)
	private String ip;
	private int port;
	@Temporal(TemporalType.DATE)
	private Date testDate;
	@Temporal(TemporalType.DATE)
	private Date lastFound;
	@Temporal(TemporalType.DATE)
	private Date firstFound;
	@Temporal(TemporalType.DATE)
	private Date testUrlDate;

}
