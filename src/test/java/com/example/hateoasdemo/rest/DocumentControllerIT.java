package com.example.hateoasdemo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("http://localhost:${local.server.port}")
    private String host;

    @Test
    void shouldGenerateLinkToDocuments() {
        // given
        final Document doc = new Document("1", "content");
        final String mapping = "/rest/v1/documents";

        // when
        final ResponseEntity<Document> response =
                restTemplate.postForEntity(mapping, doc, Document.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        final Document body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(doc.getId());
        assertThat(body.getContent()).isEqualTo(doc.getContent());
        assertThat(body.getLinks()).hasSize(1);
        assertThat(body.getLink("self")).isPresent();
        final Link selfLink = body.getLink("self").get();
        assertThat(selfLink.getHref()).isEqualTo(String.format("%s%s", host, mapping));
    }

}