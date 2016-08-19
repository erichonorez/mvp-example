package org.svomz.poc.mvpexample.presentation;

/**
 * Controls the display of a post.
 */
public interface PostPresenter {

  /**
   * Initialise the view for the specified post identifier
   *
   * @param view the calling view
   * @param aPostId the identifier of the post to diplay
   */
  void initView(PostView view, String aPostId);

}


