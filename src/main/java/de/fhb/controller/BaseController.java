package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseEntityService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

@Dependent
public abstract class BaseController<T extends BaseEntity> implements Serializable {

	private int offset = 0;
	private int amount = 20;
	private int currentPage = 0;

	protected T item;

	@EJB
	private BaseEntityService service;

	public abstract String remove();

	public abstract String edit();

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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
