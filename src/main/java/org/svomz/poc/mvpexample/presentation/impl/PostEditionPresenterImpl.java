package org.svomz.poc.mvpexample.presentation.impl;

import com.google.gson.JsonObject;

import org.svomz.poc.mvpexample.domain.PostRepository;
import org.svomz.poc.mvpexample.presentation.PostEditionPresenter;
import org.svomz.poc.mvpexample.presentation.PostEditionView;
import org.svomz.poc.mvpexample.presentation.PostForm;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public class PostEditionPresenterImpl implements PostEditionPresenter {

  private final Validator validator;
  private final PostRepository postRepository;

  public PostEditionPresenterImpl(Validator validator, PostRepository postRepository) {
    this.validator = validator;
    this.postRepository = postRepository;
  }

  public void save(PostEditionView view, PostForm form) {
    Set<ConstraintViolation<PostForm>> validations = this.validator.validate(form);
    if (!validations.isEmpty()) {
      view.displayErrors(validations);
      return;
    }

    JsonObject json = new JsonObject();
    json.addProperty("title", UUID.randomUUID().toString());
    json.addProperty("content", UUID.randomUUID().toString());
    String postId = this.postRepository.save(json);

    view.notifySuccess("Post edited!");
    view.navigateToShowPostView(postId);
  }
}
