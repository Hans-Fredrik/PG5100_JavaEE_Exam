package repository;

import java.util.List;

/**
 * Created by hffb on 15/10/15.
 */
public interface GenericDAO<T> {

    T persist(T entity);

    T update(T entity);

    T findById(int id);

    List<T> getAll();

    boolean remove(T entity);
}
