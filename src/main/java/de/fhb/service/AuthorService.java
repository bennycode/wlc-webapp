package de.fhb.service;

import de.fhb.entities.Author;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.AuthorRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class AuthorService extends BaseService<Author, AuthorRepository> {

  private static final Logger LOG = Logger.getLogger(AuthorService.class.getName());

  @EJB
  private AuthorRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public AuthorService() {
    super(Author.class);
//    cacheService = OwnCacheServerService.getInstance();
  }

  @PostConstruct
  public void init() {

  }

  @Override
  protected AuthorRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  public long getAuthorCount() {
    return repository.getAuthorCount();
  }

}
