package com.example.hateoasdemo.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

public class Document extends RepresentationModel<Document> {

    private final String id;
    private final String content;

    @JsonCreator
    public Document(@JsonProperty("id") String id, @JsonProperty("content") String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty("_links")
    public void setLinks(final Map<String, Link> links) {
        links.forEach((label, link) ->  add(link.withRel(label)) );
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
