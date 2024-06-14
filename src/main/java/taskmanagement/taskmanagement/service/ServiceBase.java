package taskmanagement.taskmanagement.service;

import java.util.List;

public interface ServiceBase<T> {

	List<T> findAll();

	void deleteById(int id);

	void delete(T entity);

	void save(T entity);

}
