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
    FacesContext context = FacesContext.getCurrentInstance();
    final ResourceBundle backendText = context.getApplication().getResourceBundle(context, ComponentFactory.BACKEND_MESSAGES_NAME);

    Difficulty[] difficulties = Difficulty.values();

    Arrays.sort(difficulties, new Comparator<Difficulty>() {
      @Override
      public int compare(Difficulty enum1, Difficulty enum2) {
        String key1 = enum1.getLabel();
        String key2 = enum2.getLabel();

        String translation1 = backendText.getString(key1);
        String translation2 = backendText.getString(key2);

        return translation1.compareToIgnoreCase(translation2);
      }
    });

    return difficulties;
  }

}
