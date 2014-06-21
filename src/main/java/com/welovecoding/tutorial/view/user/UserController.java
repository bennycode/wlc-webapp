package com.welovecoding.tutorial.view.user;

import com.welovecoding.tutorial.data.user.UserService;
import com.welovecoding.tutorial.data.user.entity.User;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserController
        extends GenFormBaseController<User, UserService> {

  @EJB
  private UserService service;

  @PostConstruct
  public void init() {
    this.item = new User();
  }

  @Override
  public String edit() {
    super.edit();

    this.item = new User();
    return Pages.ADMIN_USERS;
  }

  @Override
  public String remove() {
    super.remove();
    this.item = new User();
    return Pages.ADMIN_USERS;
  }

  @Override
  public UserService getService() {
    return service;
  }

  public long getAuthorCount() {
    return service.count();
  }
}
