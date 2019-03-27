package sample.daos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.models.Author;
import sample.util.RestAPI;

import java.util.Arrays;
import java.util.List;

public class AuthorDao implements CrudDao<Author>{

    private RestTemplate restApi = RestAPI.getInstance().restTemplate;

    public List<Author> getAllItems() {
        ResponseEntity<Author[]> response = restApi.getForEntity(RestAPI.URL_PATH + "/authors", Author[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Author getItemById() {
        return null;
    }

    @Override
    public HttpStatus addItem(Author item) {
        return null;
    }

    @Override
    public void updateItem(Author item) {
        restApi.put(RestAPI.URL_PATH + "/authors/" + item.getId(), item);
    }

    public Boolean deleteItem(Long id) {
        restApi.delete(RestAPI.URL_PATH + "/authors/" + id);
        return true;
    }
}
