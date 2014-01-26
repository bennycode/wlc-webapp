package de.fhb.service;

import de.fhb.model.Author;
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
public class AuthorService {

  private int offset = 0;
  private int amount = 0;
  private static final Logger LOG = Logger.getLogger(AuthorService.class.getName());

  @EJB
  private AuthorRepository authorRepository;

  @PostConstruct
  public void init() {
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    String offsetParam = requestParameterMap.get("offset");
    String amountParam = requestParameterMap.get("amount");

    this.offset = (offsetParam == null) ? 0 : Integer.valueOf(offsetParam);
    this.amount = (amountParam == null) ? 0 : Integer.valueOf(amountParam);
  }

  public List<Author> getAuthors() {
    Object[] logValues = new Object[]{
      this.offset,
      this.amount
    };
    LOG.log(Level.INFO, "Offset: {0}, Amount: {1}", logValues);
    return authorRepository.getAuthors(this.offset, this.amount);
  }

  public long getAuthorCount() {
    return authorRepository.getAuthorCount();
  }

  /* VIEW PARAMS */
  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

}
