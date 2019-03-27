package sample.daos;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAllItems();
    T getItemById();
    HttpStatus addItem(T item);
    void updateItem(T item);
    Boolean deleteItem(Long id);
}
