package org.glearn.jaxrs.testing.railway.service;

import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.glearn.jaxrs.testing.railway.api.Train;
import org.glearn.jaxrs.testing.railway.api.TrainType;
import org.glearn.jaxrs.testing.railway.api.impl.TrainImpl;
import org.glearn.jaxrs.testing.railway.config.RailwayJaxRSConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RailwayJaxRSConfig.class, loader = AnnotationConfigContextLoader.class)
public class TrainControllerTest {

    @Autowired
    RailwayJaxRSConfig railwayJaxRSConfig;

    RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGetTrains() throws Exception {
        // @formatter:off
        given()
                .accept(ContentType.JSON)
        .when()
                .get(railwayJaxRSConfig.getTrainsUri())
        .then()
                .statusCode(HttpStatus.SC_OK);
        // @formatter:on
    }

    @Test
    public void testGetTrain() throws Exception {
        Train train = addTrain();

        // @formatter:off
        given()
                .accept(ContentType.JSON)
        .when()
                .get(railwayJaxRSConfig.getTrainUri(train.getId()))
        .then()
                .body("id",equalTo(train.getId()))
                .statusCode(HttpStatus.SC_OK);
        // @formatter:on
    }

    @Test
    public void testGetTrain_NotFound() throws Exception {
        // @formatter:off
        given()
                .accept(ContentType.JSON)
        .when()
                .get(railwayJaxRSConfig.getTrainUri("1"))
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        // @formatter:on
    }

    @Test
    public void testAddTrain() throws Exception {
        // @formatter:off
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(getRandomTrain())
        .when()
                .post(railwayJaxRSConfig.getTrainsUri())
        .then()
                .statusCode(HttpStatus.SC_OK);
        // @formatter:on
    }

    @Test
    public void testDeleteTrain() throws Exception {
        Train train = addTrain();

        // @formatter:off
        given()
                .accept(ContentType.JSON)
        .when()
                .delete(railwayJaxRSConfig.getTrainUri(train.getId()))
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        // @formatter:on
    }

    @Test
    public void testDeleteTrain_NotFound() throws Exception {
        // @formatter:off
        given()
                .accept(ContentType.JSON)
        .when()
                .delete(railwayJaxRSConfig.getTrainUri("1"))
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        // @formatter:on
    }

    private Train addTrain() {
        return restTemplate.postForObject(railwayJaxRSConfig.getTrainsUri(), getRandomTrain(), Train.class);
    }

    private Train getRandomTrain() {
        return TrainImpl.newBuilder()
                .type(getRandomTrainType())
                .firstWay(new Date()).build();
    }

    private TrainType getRandomTrainType() {
        return TrainType.values()[new Random().nextInt(TrainType.values().length)];
    }


}
