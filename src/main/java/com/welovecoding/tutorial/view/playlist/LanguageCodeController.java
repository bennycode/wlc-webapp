package com.welovecoding.tutorial.view.playlist;

import com.welovecoding.tutorial.data.playlist.entity.LanguageCode;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
//TODO consider making this bean ApplicationScoped
@ViewScoped
public class LanguageCodeController implements Serializable {

  private static final long serialVersionUID = 1L;

  public LanguageCode[] getLanguages() {
    return LanguageCode.values();
  }

}
