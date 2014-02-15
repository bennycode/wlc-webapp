package de.fhb.service;

import de.fhb.entities.Author;
import de.fhb.repository.AuthorRepository;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class AuthorService extends BaseService<Author> {

  private static final Logger LOG = Logger.getLogger(AuthorService.class.getName());

  @EJB
  private AuthorRepository repository;

  @PostConstruct
  @Override
  public void init() {
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String offsetParam = requestParameterMap.get("offset");
    this.offset = (offsetParam == null) ? 0 : Integer.valueOf(offsetParam);
    this.currentPage = (this.offset / this.amount) + 1;
  }

  public List<Author> getAuthors() {
    Object[] logValues = new Object[]{
      this.offset,
      this.amount
    };
    LOG.log(Level.INFO, "Offset: {0}, Amount: {1}", logValues);
    return repository.getAuthors(this.offset, this.amount);
  }

  public long getAuthorCount() {
    return repository.getAuthorCount();
  }

  @Override
  public List<Author> getItems() {
    return repository.findAll();
  }

}
