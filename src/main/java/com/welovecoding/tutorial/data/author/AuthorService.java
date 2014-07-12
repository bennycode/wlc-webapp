package com.welovecoding.tutorial.data.author;

import com.welovecoding.tutorial.data.base.BaseService;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AuthorService extends BaseService<Author, AuthorRepository> {

  private static final Logger LOG = Logger.getLogger(AuthorService.class.getName());

  @EJB private AuthorRepository repository;
  @EJB private OwnCacheServerService cacheService;

  public AuthorService() {
    super(Author.class);
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

  public List<Author> orderByName() {
    return repository.orderByName();
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.AuthorDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.AuthorDTO.class.getName()
    ));
  }

}
