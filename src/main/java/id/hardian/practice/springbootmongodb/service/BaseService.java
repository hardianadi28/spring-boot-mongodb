package id.hardian.practice.springbootmongodb.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID> {

	E save(E entity);
	Optional<E> getById(ID id);
	List<E> getAll();
	
}
