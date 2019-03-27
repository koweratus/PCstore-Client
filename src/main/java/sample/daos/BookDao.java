package sample.daos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.models.Book;
import sample.util.RestAPI;

import java.util.Arrays;
import java.util.List;

public class BookDao implements CrudDao<Book> {

    private RestTemplate restApi = RestAPI.getInstance().restTemplate;

    public List<Book> getAllItems() {
        ResponseEntity<Book[]> response = restApi.getForEntity(RestAPI.URL_PATH + "/books", Book[].class);
        return Arrays.asList(response.getBody());
    }

    public Book getItemById() {
        return null;
    }

    public HttpStatus addItem(Book item) {
        ResponseEntity<Book> response = restApi.postForEntity(RestAPI.URL_PATH + "/books", item, Book.class);
        return response.getStatusCode();
    }

    public void updateItem(Book item) {
        restApi.put(RestAPI.URL_PATH + "/books/" + item.getId(), item);
    }

    public Boolean deleteItem(Long id) {
        restApi.delete(RestAPI.URL_PATH + "/books/" + id);
        return true;
    }
}
