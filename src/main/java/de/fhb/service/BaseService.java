package de.fhb.service;

import de.fhb.entities.BaseEntity;
import de.fhb.repository.AbstractRepository;

import java.util.List;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseService<T extends BaseEntity, E extends AbstractRepository<T>> {

  protected abstract E getRepository();

  public BaseService() {
  }

  public void create(T entity) {
    getRepository().create(entity);
  }

  public void edit(T entity) {
    getRepository().edit(entity);
  }

  public void remove(T entity) {
    getRepository().remove(entity);
  }

  public T find(Long id) {
    return (T) getRepository().find(id);
  }

  public List<T> findAll() {
    return (List<T>) getRepository().findAll();
  }

}
