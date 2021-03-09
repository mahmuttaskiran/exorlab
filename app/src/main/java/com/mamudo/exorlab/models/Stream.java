package com.mamudo.exorlab.models;

import java.net.URI;

public class Stream {
  URI uri;
  String title;
  String description;

  public Stream(String title, String description, URI uri) {
    this.uri = uri;
    this.title = title;
    this.description = description;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
