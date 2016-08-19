package org.svomz.poc.mvpexample.presentation;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface PostEditionView {

  /**
   * Display the errors to the user
   */
  public void displayErrors(Set<ConstraintViolation<PostForm>> capture);

  /**
   * Show a success notification message
   */
  public void notifySuccess(String message);

  /**
   * Redirect the user to display a post with the given identifier
   */
  public void navigateToShowPostView(String aPostId);
}
