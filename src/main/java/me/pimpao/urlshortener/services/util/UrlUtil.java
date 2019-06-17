package me.pimpao.urlshortener.services.util;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {
	
	@Value("${url.expiration}")
	private Long expiration;
	
	public String createNewUrl(String url) {
		int urlSize = 0;
		String[] newUrl = url.split(Pattern.quote("."));
				
		if (newUrl[1].length() < 5) {
			urlSize = 5;
		} else if (newUrl[1].length() > 36){
			urlSize = 36;
		} else {
			urlSize = newUrl[1].length();
		}
		
		char[] vet = new char[urlSize];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	
	public Long getUrlExperitionProperty() {
		return expiration;
	}
	
	public boolean expiredUrl(Long expiration) {
		Date expirationDate = new Date(expiration);
		Date now = new Date(System.currentTimeMillis());
		
		if (now.before(expirationDate)) {
			return false;
		}
		
		return true;
	}
		
	public boolean isValidUrl(String url) {
		Pattern pattern = Pattern.compile("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$");		
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}
	
	private char randomChar() {
		Random random = new Random();
		int option = random.nextInt(3);
		if (option == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (option == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}	
	
}
