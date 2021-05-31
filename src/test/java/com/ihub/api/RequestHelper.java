package com.ihub.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * <h1>RequestHelper</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public class RequestHelper {

    public static final String LIST_PRODUCT_URL = "product/list";
    public static final String ADD_PRODUCT_URL = "product/add";
    public static final String FIND_PRODUCT_URL = "product/find/%s";
    public static final String UPDATE_PRODUCT_URL = "product/update/%s";
    public static final String DELETE_PRODUCT_URL = "product/delete/%s";

    public static final String REGISTER_URL = "register";
    public static final String AUTHENTICATE_URL = "authenticate";


    public static HttpEntity getPostDefaultHeader(String jwtToken, Object content){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ jwtToken);
        headers.set("Content-Type", "application/json");

        return new HttpEntity<>(content, headers);
    }

    public static HttpEntity getDefaultHeader(String jwtToken){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ jwtToken);
        headers.set("Content-Type", "application/json");

        return new HttpEntity<>(headers);
    }

    public static URI buildURI(int port, String url) throws URISyntaxException {
        return new URI("http://localhost:" + port +"/"+ url);
    }

    public static URI buildURI(int port, String url, String... params) throws URISyntaxException {

        String paramURL = String.format(url, params);

        return new URI("http://localhost:" + port +"/"+ paramURL);
    }
}
