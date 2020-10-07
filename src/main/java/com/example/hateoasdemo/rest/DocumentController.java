package com.example.hateoasdemo.rest;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/rest/v1/documents", produces = "application/json")
public class DocumentController {

    private final List<String> documents = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("DocumentController.init");
    }

    @GetMapping
    public List<String> getDocuments() {
        return documents;
    }

    @PostMapping
    public RepresentationModel<?> addDocument(@RequestBody String document) {
        documents.add(document);
        Link link = linkTo(methodOn(DocumentController.class).getDocuments())
                            .withSelfRel()
                            .withMedia(MediaType.APPLICATION_JSON_VALUE)
                            .withTitle("Documents");
        return new RepresentationModel<>(link);
    }

}
