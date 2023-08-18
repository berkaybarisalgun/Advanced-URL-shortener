package com.barisdev.Url.Shortener.repository;

import com.barisdev.Url.Shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UrlRepository  extends JpaRepository<Url,Long> {

    Optional<Url> findByCode(String code);
}
