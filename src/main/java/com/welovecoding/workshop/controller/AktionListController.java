package com.welovecoding.workshop.controller;

import com.welovecoding.workshop.controller.AktionEditController.Mode;
import com.welovecoding.workshop.controller.navigation.Pages;
import com.welovecoding.workshop.model.Aktion;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class AktionListController implements Serializable {

  @Inject
  private AktionEditController aktionEditController;

  public String doAddAktion() {
    aktionEditController.setAktionToEdit(Mode.ADD);
    return Pages.AKTION_EDIT;
  }

  public String doEditAktion(Aktion aktion) {
    aktionEditController.setAktionToEdit(Mode.EDIT, aktion);
    return Pages.AKTION_EDIT;
  }

  public String doEditSpendeForm(Aktion aktion) {
    return Pages.SPENDE_FORM_EDIT;
  }

  public String doListSpende(Aktion aktion) {
    return Pages.SPENDE_LIST;
  }

  public void doDeleteAktion(Aktion aktion) {
//		this.aktionToDelete = aktion;
  }

}
