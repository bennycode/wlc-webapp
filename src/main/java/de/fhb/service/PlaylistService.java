package de.fhb.service;

import de.fhb.entities.Author;
import de.fhb.logging.interceptor.ServiceLoggerInterceptor;
import de.fhb.repository.AuthorRepository;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({ServiceLoggerInterceptor.class})
public class AuthorService extends BaseService<Author, AuthorRepository> {

  private static final Logger LOG = Logger.getLogger(AuthorService.class.getName());

  @EJB
  private AuthorRepository repository;

  @PostConstruct
  public void init() {

  }

  @Override
  protected AuthorRepository getRepository() {
    return repository;
  }

  public long getAuthorCount() {
    return repository.getAuthorCount();
  }

}
