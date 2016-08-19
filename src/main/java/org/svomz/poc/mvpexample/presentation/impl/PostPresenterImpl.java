package org.svomz.poc.mvpexample.presentation.impl;

import com.google.gson.JsonObject;

import org.svomz.poc.mvpexample.domain.BusinessObjectNotFoundException;
import org.svomz.poc.mvpexample.domain.PostRepository;
import org.svomz.poc.mvpexample.presentation.PostPresenter;
import org.svomz.poc.mvpexample.presentation.PostView;
import org.svomz.poc.mvpexample.presentation.PostViewModel;

public class PostPresenterImpl implements PostPresenter {

  private PostRepository postRepository;

  public PostPresenterImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void initView(PostView view, String aPostId) {
    try {
      JsonObject post = this.postRepository.findById(aPostId);

      view.bind(
        new PostViewModel(
          post.get("title").getAsString(),
          post.get("content").getAsString()));

    } catch (BusinessObjectNotFoundException e) {
      view.notifyError("The post does not exist");
      view.navigateToPostList();
    }
  }

}
