package me.pimpao.urlshortener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.pimpao.urlshortener.domain.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer>{
	
	Url findByUrl(String url);
	
	Url findByNewUrl(String newUrl);
}
