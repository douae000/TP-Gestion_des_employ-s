package DAO;

import java.util.List;

public interface GenericDAOI<T> {
    void add(T entity); 
    void delete(int id); 
    List<T> listAll(); 
    T findById(int id); 
    void update(T entity, int id);
}
