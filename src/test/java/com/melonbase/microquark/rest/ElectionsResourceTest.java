package com.melonbase.microquark.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.melonbase.microquark.repo.data.DataRoot;
import com.melonbase.microquark.rest.dto.VolksabstimmungDto;
import com.melonbase.microquark.rest.dto.VorlageDto;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import one.microstream.storage.types.StorageManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(VolksabstimmungResource.class)
public class ElectionsResourceTest {

  @Inject
  StorageManager storage;

  @BeforeEach
  void setUp() {
    storage.setRoot(new DataRoot());
    storage.storeRoot();
  }

  @Test
  void getElection_forUnknownId_resultNotFound() {
    // given
    var id = 42;

    // when
    var response = given()
        .when()
        .get(Integer.toString(id));

    // then
    response.then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  void addElection_returnsCorrectStatusCode() throws JsonProcessingException {
    // given
    // https://www.bk.admin.ch/ch/d/pore/va/18930820/index.html
    var volksabstimmung = new VolksabstimmungDto(
        LocalDate.of(1893, Month.AUGUST, 20),
        List.of(new VorlageDto(
            "Eidgenössische Volksinitiative 'für ein Verbot des Schlachtens ohne vorherige Betäubung'",
            BigDecimal.valueOf(49.18)
        ))
    );
    var given = given()
        .body(volksabstimmung)
        .contentType(ContentType.JSON);

    // when
    var response = given.when()
        .post();

    // then
    response.then()
        .statusCode(HttpStatus.SC_CREATED);
  }

  @Test
  void getElections_noElections_resultEmpty() {
    // given
    var given = given();

    // when
    var response = given.when()
        .get();

    // then
    response.then()
        .statusCode(HttpStatus.SC_OK)
        .body(is("[]"));
  }
}
