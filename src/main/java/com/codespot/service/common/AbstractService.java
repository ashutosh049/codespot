package com.codespot.service.common;

import java.io.Serializable;
import java.util.List;

import com.codespot.dao.common.IOperations;

public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	public T findOne(final long id) {
		return getDao().findOne(id);
	}

	public List<T> findAll() {
		return getDao().findAll();
	}

	public T create(final T entity) {
		System.out.println("AbstractService.create...");
		 return getDao().create(entity);
	}

	public T update(final T entity) throws Exception {
		return getDao().update(entity);
	}

	public void delete(final T entity) {
		getDao().delete(entity);
	}

	public void deleteById(final long entityId) {
		getDao().deleteById(entityId);
	}

	protected abstract IOperations<T> getDao();

}
