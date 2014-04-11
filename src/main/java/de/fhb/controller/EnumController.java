package de.fhb.controller;

import de.fhb.entities.Provider;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
/**
 * http://stackoverflow.com/questions/8229638/how-to-make-a-dropdown-menu-of-a-enum-in-jsf
 */
public class EnumController {

  public Provider[] getProviderValues() {
    return Provider.values();
  }

}
