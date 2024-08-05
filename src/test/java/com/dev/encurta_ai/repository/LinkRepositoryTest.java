package com.dev.encurta_ai.repository;

import com.dev.encurta_ai.model.Link;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
@Transactional
class LinkRepositoryTest {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Should fetch the original url from the short url")
    void findByUrlShortSucess() {
        Link link = this.createLink();

        String urlOriginal = this.linkRepository.findByUrlShort(link.getUrlShort());

        assertThat(urlOriginal).isEqualTo(link.getUrlLong());
    }

    @Test
    @DisplayName("Should return empty")
    void findByUrlShortFail() {
        String urlOriginal = this.linkRepository.findByUrlShort("Url short");

        assertThat(urlOriginal).isNull();
    }


    private Link createLink(){
        Link link = new Link();
        link.setUrlLong("Url long");
        link.setUrlShort("Url short");
        link.setUrlQrCode("QR code");
        this.em.persist(link);
        return link;
    }
}