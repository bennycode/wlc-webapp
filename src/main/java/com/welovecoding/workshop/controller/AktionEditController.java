package com.welovecoding.workshop.controller;

import com.welovecoding.workshop.controller.navigation.Pages;
import com.welovecoding.workshop.data.AktionListProducer;
import com.welovecoding.workshop.model.Aktion;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class AktionEditController implements Serializable {

  @Inject
  private AktionListProducer aktionListProducer;

  public enum Mode {

    EDIT, ADD
  }

  private Aktion aktion;
  private Mode mode;

  public Aktion getAktion() {
    return aktion;
  }

  public Mode getMode() {
    return mode;
  }

  public void setAktionToEdit(Mode mode) {
    setAktionToEdit(mode, new Aktion());
  }

  public void setAktionToEdit(Mode mode, Aktion aktion) {
    this.mode = mode;
    this.aktion = aktion;
  }

  public String doSave() {
    if (this.mode == Mode.ADD) {
      aktionListProducer.getAktionen().add(aktion);
    }

    return Pages.AKTION_LIST;
  }

  public String doCancel() {
    return Pages.AKTION_LIST;
  }

  public String getTitle() {
    if (this.mode == Mode.ADD) {
      return "Aktion editieren";
    } else {
      return "Neue Aktion anlegen";
    }
  }

}
