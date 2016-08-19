package org.svomz.poc.mvpexample.presentation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Holds data to edit a post and contains validation annotations.
 */
public class PostForm {

  @NotNull
  @Size(min = 1, max = 100)
  public String title;

  @NotNull
  @Size(min = 1, max = 2048)
  public String content;

  public void setContent(String content) {
    this.content = content;
  }
}
