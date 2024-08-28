package com.dev.encurta_ai.repository;

import com.dev.encurta_ai.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = "SELECT url_long FROM tb_link where url_short = :urlShort", nativeQuery = true)
    String findByUrlShort(String urlShort);

    boolean existsByUrlShort(String urlShort);
}
