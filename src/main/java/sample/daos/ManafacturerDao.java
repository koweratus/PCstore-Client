package sample.daos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.models.Component;
import sample.models.Manafacturer;
import sample.util.RestAPI;

import java.util.Arrays;
import java.util.List;

public class ManafacturerDao implements CrudDao<Manafacturer>{

    private RestTemplate restApi = RestAPI.getInstance().restTemplate;

    public List<Manafacturer> getAllItems() {
        ResponseEntity<Manafacturer[]> response = restApi.getForEntity(RestAPI.URL_PATH + "/manafacturers", Manafacturer[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Manafacturer getItemById() {
        return null;
    }

    @Override
    public HttpStatus addItem(Manafacturer item) {
        ResponseEntity<Manafacturer> response = restApi.postForEntity(RestAPI.URL_PATH + "/manafacturers", item, Manafacturer.class);
        return response.getStatusCode();
    }

    @Override
    public void updateItem(Manafacturer item) {
        restApi.put(RestAPI.URL_PATH + "/manafacturers/" + item.getId(), item);
    }

    public Boolean deleteItem(Long id) {
        restApi.delete(RestAPI.URL_PATH + "/manafacturers/" + id);
        return true;
    }
}
