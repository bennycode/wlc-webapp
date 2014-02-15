package de.fhb.service;

import de.fhb.entities.BaseEntity;
import de.fhb.repository.BaseEntityRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseService<T extends BaseEntity> {

	@EJB
	private BaseEntityRepository repository;

	public BaseService() {
	}

	public void create(T entity) {
		repository.create(entity);
	}

	public void edit(T entity) {
		repository.edit(entity);
	}

	public void remove(T entity) {
		repository.remove(entity);
	}

	public T find(Long id) {
		return (T) repository.find(id);
	}

	public List<T> findAll() {
		return (List<T>) repository.findAll();
	}

}
