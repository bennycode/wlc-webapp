package de.fhb.service;

import de.fhb.model.Author;
import de.fhb.repository.AuthorRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AuthorService {

  @EJB
  private AuthorRepository authorRepository;

  public List<Author> getAuthors() {
    return authorRepository.getCategories();
  }
}
