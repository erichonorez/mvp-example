package org.svomz.poc.mvpexample.presentation;

/**
 * Saves the form to edit an existing post.
 */
public interface PostEditionPresenter {

  void save(PostEditionView view, PostForm form);
}
