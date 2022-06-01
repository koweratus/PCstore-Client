package sample.util;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestAPI {
    private static RestAPI instance = new RestAPI();
    private List<HttpMessageConverter<?>> messageConverters;
    public RestTemplate restTemplate;
    public final static String URL_PATH = "http://localhost:8080/api";

    private RestAPI(){
        messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypes);
        messageConverters.add(converter);

        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
    }

    public static RestAPI getInstance(){
        return instance;
    }
}
