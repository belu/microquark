package com.melonbase.microquark.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.melonbase.microquark.repo.data.DataRoot;
import com.melonbase.microquark.rest.dto.in.NewElection;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import one.microstream.storage.types.StorageManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(ElectionsResource.class)
public class ElectionsResourceTest {

  @Inject
  StorageManager storage;

  @BeforeEach
  void setUp() {
    storage.setRoot(new DataRoot());
    storage.storeRoot();
  }

  @Test
  void addElection_correctResult() throws JsonProcessingException {
    var election = new NewElection(
        LocalDate.now(),
        List.of("Freitag zum gesetzlichen Feiertag erklären?")
    );

    given()
        .when()
        .body(election)
        .contentType(ContentType.JSON)
        .post()
        .then()
        .statusCode(HttpStatus.SC_CREATED);
  }

  @Test
  void getElections_noElections_resultEmpty() {
    given()
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body(is("[]"));
  }
}
