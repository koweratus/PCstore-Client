package sample.daos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.models.Component;
import sample.util.RestAPI;

import java.util.Arrays;
import java.util.List;

public class ComponentDao implements CrudDao<Component> {

    private RestTemplate restApi = RestAPI.getInstance().restTemplate;

    public List<Component> getAllItems() {
        ResponseEntity<Component[]> response = restApi.getForEntity(RestAPI.URL_PATH + "/components", Component[].class);
        return Arrays.asList(response.getBody());
    }

    public Component getItemById() {
        return null;
    }

    public HttpStatus addItem(Component item) {
        ResponseEntity<Component> response = restApi.postForEntity(RestAPI.URL_PATH + "/components", item, Component.class);
        return response.getStatusCode();
    }

    public void updateItem(Component item) {
        restApi.put(RestAPI.URL_PATH + "/components/" + item.getId(), item);
    }

    public Boolean deleteItem(Long id) {
        restApi.delete(RestAPI.URL_PATH + "/components/" + id);
        return true;
    }
}
