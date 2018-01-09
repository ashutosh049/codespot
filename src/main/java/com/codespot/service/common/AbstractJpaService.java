package com.codespot.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.codespot.dao.common.IOperations;

@Transactional(value = "transactionManager")
public abstract class AbstractJpaService<T extends Serializable> extends AbstractService<T>implements IOperations<T> {

	@Override
	public T findOne(final long id) {
		return super.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return super.findAll();
	}

	@Override
	public T create(final T entity) {
		System.out.println("AbstractJpaService.create...");
		return super.create(entity);
	}

	@Override
	public T update(final T entity) throws Exception {
		return super.update(entity);
	}

	@Override
	public void delete(final T entity) {
		super.delete(entity);
	}

	@Override
	public void deleteById(final long entityId) {
		super.deleteById(entityId);
	}

}
