package com.welovecoding.tutorial.view.filter;

import com.welovecoding.tutorial.view.JSFUtils;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * http://nnisansala.blogspot.de/p/how-to-change-locale-of-jsf-web.html
 */
public class LanguageFilter implements PhaseListener {
  
  private static final Logger LOG = Logger.getLogger(LanguageFilter.class.getName());
  
  @Override
  public void afterPhase(PhaseEvent event) {
    FacesContext facesContext = event.getFacesContext();
    Locale locale = JSFUtils.getLocaleFromDomain(facesContext);
    LOG.log(Level.INFO, "View content locale: {0}", locale.getLanguage());
    facesContext.getViewRoot().setLocale(locale);
  }
  
  @Override
  public void beforePhase(PhaseEvent event) {
    
  }
  
  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }
  
}
