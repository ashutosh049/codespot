package com.codespot.dao.common;
import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable>{
	
    T findOne(final long id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity) throws Exception;

    void delete(final T entity);

    void deleteById(final long entityId);

}