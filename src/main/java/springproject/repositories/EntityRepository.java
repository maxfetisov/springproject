package springproject.repositories;

import java.util.Collection;
import java.util.Optional;

public interface EntityRepository<T, E> {
    void create(T entity);
    Collection<T> getAll();
    Optional<T> getById(E id);
    void update(T entity);
    void delete(E id);
}
