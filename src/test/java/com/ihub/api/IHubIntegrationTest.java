package com.ihub.api;

import com.ihub.api.dao.UserDao;
import com.ihub.api.databuilder.ProductDataBuilder;
import com.ihub.api.databuilder.UserDtoDataBuilder;
import com.ihub.api.model.domainobject.JwtRequest;
import com.ihub.api.model.domainobject.JwtResponse;
import com.ihub.api.model.domainobject.Product;
import com.ihub.api.model.domainobject.User;
import com.ihub.api.model.datatransferobject.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static com.ihub.api.RequestHelper.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.GET;

/**
 * <h1>IHubIntegrationTest</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IHubIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserDao userDao;

    private String jwtToken;

    private User currentUser;

    @Before
    public void init() throws URISyntaxException {
        UserDto userDto = UserDtoDataBuilder.getDefaultUser();
        currentUser = registerUser(userDto);
        this.jwtToken = getJWTToker(currentUser);
    }

    @After
    public void tearDown(){
        userDao.delete(currentUser);
    }

    private String getJWTToker(User user) throws URISyntaxException {

        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setPassword(user.getPassword());
        authenticationRequest.setUsername(user.getUsername());
        ResponseEntity<JwtResponse> jwtResponseResponseEntity =
                this.restTemplate.postForEntity(buildURI(port, AUTHENTICATE_URL), authenticationRequest, JwtResponse.class);

        return jwtResponseResponseEntity.getBody().getJwttoken();
    }

    private User registerUser(UserDto user) throws URISyntaxException {

        ResponseEntity<User> userResponse =
                this.restTemplate.postForEntity(buildURI(port, REGISTER_URL), user, User.class);

        User userObj = userResponse.getBody();
        userObj.setPassword(user.getPassword());
        userObj.setUsername(user.getUsername());

        return userObj;
    }

    @Test
    public void testDeleteProduct() throws Exception {

        Product product = ProductDataBuilder.getTV();
        HttpEntity requestADD = getPostDefaultHeader(this.jwtToken, product);

        //add product
        ResponseEntity<Product> result = restTemplate.postForEntity(buildURI(port, ADD_PRODUCT_URL), requestADD, Product.class);
        Product productAdded = result.getBody();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        String productId = String.valueOf(productAdded.getId());

        //delete product
        URI uri = buildURI(port, DELETE_PRODUCT_URL, productId);

        HttpEntity requestDELETE = getDefaultHeader(this.jwtToken);
        ResponseEntity<String> resultDelete = restTemplate.exchange(uri, HttpMethod.DELETE, requestDELETE, String.class);

        assertThat(resultDelete.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));

        //find product
        ResponseEntity<Product> resultFind = restTemplate.exchange(buildURI(port, FIND_PRODUCT_URL, productId), GET, getDefaultHeader(this.jwtToken), Product.class);
        Product productActual = resultFind.getBody();

        assertThat(resultFind.getStatusCode(), equalTo(HttpStatus.OK));

        Assertions.assertThat(productActual).isNull();
    }

    @Test
    public void testUpdateProduct() throws Exception {

        Product product = ProductDataBuilder.getTV();
        HttpEntity request = getPostDefaultHeader(this.jwtToken, product);

        //add product
        ResponseEntity<Product> result = restTemplate.postForEntity(buildURI(port, ADD_PRODUCT_URL), request, Product.class);
        Product productAdded = result.getBody();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        String productId = String.valueOf(productAdded.getId());

        //update product
        URI uri = buildURI(port, UPDATE_PRODUCT_URL, productId);
        productAdded.setName("TV_NAME_UPDATED");

        HttpEntity requestPOST = getPostDefaultHeader(this.jwtToken, productAdded);

        Product productActual = restTemplate.patchForObject(uri, requestPOST, Product.class);

        assertThat(productAdded.getPrice(), equalTo(productActual.getPrice()));
        assertThat(productAdded.getId(), equalTo(productActual.getId()));
        assertThat(productAdded.getName(), equalTo(productActual.getName()));
    }

    @Test
    public void testFindProduct() throws Exception {

        Product product = ProductDataBuilder.getTV();

        HttpEntity requestGET = getDefaultHeader(this.jwtToken);
        HttpEntity request = getPostDefaultHeader(this.jwtToken, product);

        //add product
        ResponseEntity<Product> result = restTemplate.postForEntity(buildURI(port, ADD_PRODUCT_URL), request, Product.class);
        Product productAdded = result.getBody();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        String productId = String.valueOf(productAdded.getId());

        //find product
        URI uri = buildURI(port, FIND_PRODUCT_URL, productId);

        ResponseEntity<Product> resultFind = restTemplate.exchange(uri, GET, requestGET, Product.class);
        Product productActual = resultFind.getBody();

        assertThat(productAdded.getPrice(), equalTo(productActual.getPrice()));
        assertThat(productAdded.getId(), equalTo(productActual.getId()));
        assertThat(productAdded.getName(), equalTo(productActual.getName()));
    }

    @Test
    public void testAddProduct() throws Exception {

        Product product = ProductDataBuilder.getTV();

        HttpEntity request = getPostDefaultHeader(this.jwtToken, product);

        ResponseEntity<Product> result = restTemplate.postForEntity(buildURI(port, ADD_PRODUCT_URL), request, Product.class);
        Product productActual = result.getBody();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        assertThat(productActual.getPrice(), equalTo(product.getPrice()));
        assertThat(productActual.getId(), notNullValue());
        assertThat(productActual.getName(), equalTo(product.getName()));
    }


    @Test
    public void testAuthorizedAccess() throws Exception {

        HttpEntity<String> request = getDefaultHeader(this.jwtToken);

        ResponseEntity<String> result = restTemplate.exchange(buildURI(port, LIST_PRODUCT_URL), GET, request, String.class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {

        ResponseEntity<String> serverResponse = this.restTemplate.getForEntity(buildURI(port, LIST_PRODUCT_URL), String.class);

        assertThat(serverResponse.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

}
