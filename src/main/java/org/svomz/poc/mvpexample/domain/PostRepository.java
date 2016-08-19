package org.svomz.poc.mvpexample.domain;

import com.google.gson.JsonObject;


/**
 * Re-creates Post from storage.
 */
public interface PostRepository {

  /**
   * Fetches a post form persistence storage or throw an exception
   * @throws {@link BusinessObjectNotFoundException} if not found.
   */
  public JsonObject findById(String aPostId)
    throws BusinessObjectNotFoundException;

  /**
   * Saves the json in the backend storage.
   * @return the id of the saved object
   */
  String save(JsonObject json);
}

