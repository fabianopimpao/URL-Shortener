package me.pimpao.shortener.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.pimpao.urlshortener.UrlShortenerApplication;
import me.pimpao.urlshortener.domain.Url;
import me.pimpao.urlshortener.dto.UrlDTO;
import me.pimpao.urlshortener.services.UrlService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { UrlShortenerApplication.class })
public class UrlShortenerApplicationTests {
	
	private UrlDTO urlDto;

	private String hostUrl;
	
	@Autowired
	private UrlService urlService;
	
	private Url url;
	
	@Before
	public void setup() {
		urlDto = new UrlDTO("http://www.zambas.com.br");
		hostUrl = "http://localhost:8080/";				
	}

	@Test
	public void contextLoads() {
		url = urlService.fromDto(urlDto, hostUrl);		
		Assert.assertNotNull(url);
		
		url = urlService.insert(url);
		
		Assert.assertNotNull(url);
		
		url = urlService.findByNewUrl(url.getNewUrl());
		
		Assert.assertEquals(urlDto.getUrl(), url.getUrl());
	}
}
