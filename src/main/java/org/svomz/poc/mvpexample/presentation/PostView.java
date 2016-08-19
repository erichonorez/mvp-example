package org.svomz.poc.mvpexample.presentation;

/**
 * Displays a post to the user
 */
public interface PostView {

  /**
   * Binds properties of the PostViewModel to visual components.
   */
  void bind(PostViewModel viewModel);

  /**
   * Displays the given error to the user.
   */
  void notifyError(String anErrorDescription);

  /**
   * Go to the list of posts.
   */
  void navigateToPostList();
}


