package com.example.hateoasdemo.rest;

import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/rest/v1/documents")
public class DocumentController {

    private final List<Document> documents = new ArrayList<>();

    @GetMapping
    public List<Document> getDocuments() {
        return documents;
    }

    @PostMapping
    public Document addDocument(@RequestBody Document document) {
        documents.add(document);
        final Link link = linkTo(methodOn(DocumentController.class).getDocuments())
                                  .withSelfRel()
                                  .withMedia(MediaType.APPLICATION_JSON_VALUE)
                                  .withTitle("Documents");
        document.add(link);
        return document;
    }

}
