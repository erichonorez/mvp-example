package org.svomz.poc.mvpexample.presentation;


import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.ArgumentCaptor;
import org.svomz.poc.mvpexample.domain.PostRepository;
import org.svomz.poc.mvpexample.presentation.impl.PostEditionPresenterImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  PostEditionPresenterImplUnitTest.WhenFormIsValid.class,
  PostEditionPresenterImplUnitTest.WhenFormIsNotValid.class
})
public class PostEditionPresenterImplUnitTest {

  public static class WhenFormIsNotValid {

    @Test
    public void itShouldDisplayErrorWhenTitleIsEmpty() {
      // When the title of the form has not been filled in
      PostForm form = new PostForm();
      form.setContent("Content");

      // Then the error must be displayed to the user
      Validator validator = Validation
        .buildDefaultValidatorFactory().getValidator();

      PostEditionPresenter presenter
        = new PostEditionPresenterImpl(validator, mock(PostRepository.class));
      PostEditionView view = mock(PostEditionView.class);

      presenter.save(view, form);

      Class<Set<ConstraintViolation<PostForm>>> argumentClass =
        (Class<Set<ConstraintViolation<PostForm>>>) (Class) Set.class;

      ArgumentCaptor<Set<ConstraintViolation<PostForm>>> argument =
        ArgumentCaptor.forClass(argumentClass);

      // Then the application should show errors to user
      verify(view).displayErrors(argument.capture());

      Set<ConstraintViolation<PostForm>> validations = argument.getValue();
      assertThat(validations).hasSize(1);
    }

  }


  public static class WhenFormIsValid {

    @Test
    public void itShouldNotifyUser() {
      // When the post form is valid
      PostForm form = new PostForm();
      Validator validator = mock(Validator.class);
      when(validator.validate(form))
        .thenReturn(new HashSet<ConstraintViolation<PostForm>>());

      PostRepository postRepository = mock(PostRepository.class);
      when(postRepository.save(any(JsonObject.class)))
        .thenReturn(UUID.randomUUID().toString());

      PostEditionPresenter presenter
        = new PostEditionPresenterImpl(validator, postRepository);
      PostEditionView view = mock(PostEditionView.class);

      presenter.save(view, form);

      // Then the application should notify the user
      verify(view).notifySuccess(anyString());
    }

    @Test
    public void itShouldRedirectTheUserToTheShowPostView() {
      // When the form post is valid
      PostForm form = new PostForm();
      Validator validator = mock(Validator.class);
      when(validator.validate(form))
        .thenReturn(new HashSet<ConstraintViolation<PostForm>>());

      PostRepository postRepository = mock(PostRepository.class);
      String aPostId = UUID.randomUUID().toString();
      when(postRepository.save(any(JsonObject.class)))
        .thenReturn(aPostId);

      PostEditionPresenter presenter = new PostEditionPresenterImpl(validator, postRepository);
      PostEditionView view = mock(PostEditionView.class);

      presenter.save(view, form);

      // Then the application should redirect the user
      verify(view).navigateToShowPostView(aPostId);
    }
  }

}
