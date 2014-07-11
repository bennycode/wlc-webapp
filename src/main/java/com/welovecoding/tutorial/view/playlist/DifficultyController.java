package com.welovecoding.tutorial.view.playlist;

import com.welovecoding.tutorial.data.playlist.entity.Difficulty;
import com.welovecoding.tutorial.view.scaffolding.ComponentFactory;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
//TODO consider making this bean ApplicationScoped
@ViewScoped
public class DifficultyController implements Serializable {

  private static final long serialVersionUID = 1L;

  public Difficulty[] getDifficulties() {
    return Difficulty.values();
  }

}
