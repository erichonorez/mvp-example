package org.svomz.poc.mvpexample.presentation;

public class PostViewModel {

  private final String title;
  private final String content;

  public PostViewModel(String aTitle, String aContent) {
    this.title = aTitle;
    this.content = aContent;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }
}
