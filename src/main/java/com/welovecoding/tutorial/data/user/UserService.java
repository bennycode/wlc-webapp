package com.welovecoding.tutorial.data.user;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.user.entity.User;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService extends BaseService<User, UserRepository> {

  private static final Logger LOG = Logger.getLogger(UserService.class.getName());

  @EJB
  private UserRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public UserService() {
    super(User.class);
  }

  @PostConstruct
  public void init() {
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  protected UserRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(new String[]{}));
  }

  @Override
  protected void invalidateRelatedCaches() {
    //NO-OP
    // UserService is not relevant for the REST-Service nor the associated Cache
  }

}
