package com.welovecoding.controller;

import com.welovecoding.entities.User;
import com.welovecoding.service.UserService;
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
