package com.welovecoding.tutorial.data.base;

import java.util.Date;
import javax.interceptor.ExcludeClassInterceptors;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 *
 * This is the BaseEntity listener with external callback methods for
 * controlling the state of the field lastModified and created.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
public class EntityListener {

  /**
   * invoked before a new entity is persisted.
   *
   * @param be The Entity which will be persisted.
   */
  @PrePersist
  private void onPrePersist(BaseEntity be) {
    be.setCreated(new Date());
    be.setLastModified(new Date());
  }

  /**
   * invoked after storing a new entity in the database (during commit or
   * flush).
   *
   * @param be The Entity which been persisted.
   */
  @PostPersist
  @ExcludeClassInterceptors
  private void onPostPersist(BaseEntity be) {
  }

  /**
   * invoked after an entity has been retrieved from the database.
   *
   * @param be The Entity which been loaded.
   */
  @PostLoad
  @ExcludeClassInterceptors
  private void onPostLoad(BaseEntity be) {
  }

  /**
   * invoked when an entity is identified as modified by the EntityManager.
   *
   * @param be The Entity which will be updated.
   */
  @PreUpdate
  private void onPreUpdate(BaseEntity be) {
    be.setLastModified(new Date());
    if (be.getCreated() == null) {
      be.setCreated(new Date());
    }
  }

  /**
   * invoked after updating an entity in the database (during commit or flush).
   *
   * @param be The Entity which been updated.
   */
  @PostUpdate
  @ExcludeClassInterceptors
  private void onPostUpdate(BaseEntity be) {
  }

  /**
   * invoked when an entity is marked for removal in the EntityManager.
   *
   * @param be The Entity which will be removed.
   */
  @PreRemove
  @ExcludeClassInterceptors
  void onPreRemove(BaseEntity be) {
  }

  /**
   * invoked after deleting an entity from the database (during commit or
   * flush).
   *
   * @param be The Entity which been removed.
   */
  @PostRemove
  @ExcludeClassInterceptors
  private void onPostRemove(BaseEntity be) {
  }
}
