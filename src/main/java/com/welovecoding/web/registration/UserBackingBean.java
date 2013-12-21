package com.welovecoding.web.registration;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBackingBean implements Serializable {

  @EJB
  private UserRepository userRepository;

  private final static Logger LOGGER = Logger.getLogger(UserBackingBean.class.getSimpleName());

  private String name;
  private String email;
  private String password;

  public UserBackingBean() {
  }

  public String submit() {
    String redirect = "/error?faces-redirect=true";

    String template = "Name: {0},  Email: {1}, Password: {2}";
    Object[] values = new Object[]{
      this.name,
      this.email,
      this.password
    };
    LOGGER.log(Level.INFO, template, values);

    boolean isValid = validate();
    if (isValid) {
      UserEntity entity = new UserEntity(name, email, password);
      userRepository.save(entity);
      LOGGER.log(Level.INFO, "Saved user.");
      redirect = "/success?faces-redirect=true";
    }

    return "";
  }

  public void checkName(FacesContext context, UIComponent component, Object value) {
    if (name == null || name != null && name.length() < 1) {
      FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage("Please enter your name."));
    }
  }

  public boolean validate() {
    return name.length() > 1 && email.length() > 1 && password.length() > 1;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
