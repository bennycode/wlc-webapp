package de.fhb.service;

import de.fhb.entities.BaseEntity;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.BaseEntityRepository;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class BaseEntityService extends BaseService<BaseEntity, BaseEntityRepository> {

  private static final Logger LOG = Logger.getLogger(BaseEntityService.class.getName());

  @EJB
  private BaseEntityRepository repository;

  @PostConstruct
  public void init() {

  }

  @Override
  protected BaseEntityRepository getRepository() {
    return repository;
  }

}
