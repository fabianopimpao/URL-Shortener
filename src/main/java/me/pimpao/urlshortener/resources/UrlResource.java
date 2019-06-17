package me.pimpao.urlshortener.resources;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.pimpao.urlshortener.domain.Url;
import me.pimpao.urlshortener.dto.UrlDTO;
import me.pimpao.urlshortener.services.UrlService;

@RestController
public class UrlResource {
	
	@Autowired
	private UrlService urlService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Url> find(HttpServletRequest request) {
		String newUrl = request.getRequestURL().toString();
		Url url = urlService.findByNewUrl(newUrl);
		String redirectTo = url.getUrl();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(redirectTo));
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Url> insert(@Valid @RequestBody UrlDTO obj, HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		Url url = urlService.fromDto(obj, requestUrl);
		url = urlService.insert(url);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{shortUrl}").buildAndExpand(url.getPath()).toUri();
		return ResponseEntity.created(uri).body(url);
	}
	
	
}
