package com.barisdev.Url.Shortener.repository;

import com.barisdev.Url.Shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository  extends JpaRepository<Url,Long> {

    Optional<Url> findByref(String ref);
}
