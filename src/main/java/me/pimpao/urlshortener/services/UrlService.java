package me.pimpao.urlshortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.pimpao.urlshortener.domain.Url;
import me.pimpao.urlshortener.dto.UrlDTO;
import me.pimpao.urlshortener.repositories.UrlRepository;
import me.pimpao.urlshortener.services.exceptions.ExpiredUrlException;
import me.pimpao.urlshortener.services.exceptions.InvalidUrlException;
import me.pimpao.urlshortener.services.exceptions.ObjectNotFoundException;
import me.pimpao.urlshortener.services.util.UrlUtil;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	@Autowired
	private UrlUtil urlUtil;
	
	public Url insert(Url url) {
		Url obj = urlRepository.findByUrl(url.getUrl());
		if (obj == null) {			
			obj = urlRepository.save(url);
		} else if (urlUtil.expiredUrl(obj.getExpiresAt())) {
			urlRepository.deleteById(obj.getId());
			obj = urlRepository.save(url);
		}
		
		return obj;		
	}
	
	public Url findByNewUrl(String newUrl) {
		Url url = urlRepository.findByNewUrl(newUrl);		
		if (url == null) {
			throw new ObjectNotFoundException("Url inexistente");
		} else if (urlUtil.expiredUrl(url.getExpiresAt())) {
			urlRepository.deleteById(url.getId());
			throw new ExpiredUrlException("Url expirada");
		}
		
		return url; 
	}
	
	public Url fromDto(Integer id, String url, String newUrl, String path, Long expiresAt) {
		return new Url(null, url, newUrl, path, expiresAt);
	}
	
	public Url fromDto(UrlDTO objDto, String requestUrl) {
		boolean isValid = urlUtil.isValidUrl(objDto.getUrl());
		if (isValid) {
			String path = urlUtil.createNewUrl(objDto.getUrl());
			String newUrl = requestUrl + path;
			return fromDto(null, objDto.getUrl(), newUrl, path, System.currentTimeMillis() + urlUtil.getUrlExperitionProperty());			
		}		
		throw new InvalidUrlException("Url inválida");
	}
	
}
