package repository;

import java.util.List;

public interface Dao<T> {
    void save(T value);
    void update(T value);
    void delete(int id);
    T get(int id);
    List<T> getAll();
}
