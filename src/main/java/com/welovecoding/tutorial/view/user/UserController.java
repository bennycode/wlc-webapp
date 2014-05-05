package com.welovecoding.tutorial.view.user;

import com.welovecoding.tutorial.view.base.BaseController;
import com.welovecoding.tutorial.data.user.entity.User;
import com.welovecoding.tutorial.data.user.UserService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserController
        extends BaseController<User, UserService> {

  @EJB
  private UserService service;

  @PostConstruct
  public void init() {
  }

  @Override
  public UserService getService() {
    return service;
  }
}
