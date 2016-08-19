package org.svomz.poc.mvpexample.presentation;

import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.ArgumentCaptor;
import org.svomz.poc.mvpexample.domain.BusinessObjectNotFoundException;
import org.svomz.poc.mvpexample.domain.PostRepository;
import org.svomz.poc.mvpexample.presentation.impl.PostPresenterImpl;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  PostPresenterImplUnitTest.WhenPostDoesNotExist.class,
  PostPresenterImplUnitTest.WhenPostExist.class
})
public class PostPresenterImplUnitTest {

  public static class WhenPostExist {

    @Test
    public void itShouldDisplayPostById() throws BusinessObjectNotFoundException {
      // Given a post with id "0f4d110b-3137-4bfb-8e2d-64099c9c3401" exists
      String aPostId = "0f4d110b-3137-4bfb-8e2d-64099c9c3401";
      String aTitle = "Title";
      String aContent = "Content";

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("title", aTitle);
      jsonObject.addProperty("content", aContent);

      PostRepository postRepository = mock(PostRepository.class);
      when(postRepository.findById(aPostId))
        .thenReturn(jsonObject);

      // When the user goes to the post page
      PostPresenter presenter = new PostPresenterImpl(postRepository);
      PostView view = mock(PostView.class);
      presenter.initView(view, aPostId);

      // Then the post is displayed
      ArgumentCaptor<PostViewModel> argument
        = ArgumentCaptor.forClass(PostViewModel.class);

      verify(view).bind(argument.capture());

      PostViewModel viewModel = argument.getValue();

      assertThat(viewModel.getTitle())
        .isEqualTo(aTitle);

      assertThat(viewModel.getContent())
        .isEqualTo(aContent);
    }

  }

  public static class WhenPostDoesNotExist {

    @Test
    public void itShouldNotifyTheUser() throws BusinessObjectNotFoundException {
      // Given a post with id "0f4d110b-3137-4bfb-8e2d-64099c9c3401" does not exist
      String aPostId = "0f4d110b-3137-4bfb-8e2d-64099c9c3401";

      PostRepository postRepository = mock(PostRepository.class);
      when(postRepository.findById(aPostId))
        .thenThrow(new BusinessObjectNotFoundException());

      // When the user goes to the post page
      PostPresenter presenter = new PostPresenterImpl(postRepository);
      PostView view = mock(PostView.class);
      presenter.initView(view, aPostId);

      verify(view).notifyError(anyString());
    }


    @Test
    public void itShouldRedirectTheUserToPostList() throws BusinessObjectNotFoundException {
      // Given a post with id "0f4d110b-3137-4bfb-8e2d-64099c9c3401" does not exist
      String aPostId = "0f4d110b-3137-4bfb-8e2d-64099c9c3401";

      PostRepository postRepository = mock(PostRepository.class);
      when(postRepository.findById(aPostId))
        .thenThrow(new BusinessObjectNotFoundException());

      // When the user goes to the post page
      PostPresenter presenter = new PostPresenterImpl(postRepository);
      PostView view = mock(PostView.class);
      presenter.initView(view, aPostId);

      verify(view).navigateToPostList();
    }

  }

}
