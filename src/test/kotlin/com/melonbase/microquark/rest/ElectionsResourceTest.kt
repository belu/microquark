package com.melonbase.microquark.rest

import com.melonbase.microquark.repo.data.DataRoot
import com.melonbase.microquark.rest.dto.inbound.NeueVolksabstimmung
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import one.microstream.storage.types.StorageManager
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@QuarkusTest
@TestHTTPEndpoint(VolksabstimmungResource::class)
class ElectionsResourceTest {

  @Inject
  lateinit var storage: StorageManager

  @BeforeEach
  fun setUp() {
    storage.setRoot(DataRoot())
    storage.storeRoot()
  }

  @Test
  fun election_forUnknownId_resultNotFound() {
    given()
      .`when`()
      .get("1980-07-03")
      .then()
      .statusCode(HttpStatus.SC_NOT_FOUND)
  }

  @Test
  fun addElection_returnsCorrectStatusCode() {
    // https://www.bk.admin.ch/ch/d/pore/va/18930820/index.html
    val volksabstimmung = NeueVolksabstimmung(
      LocalDate.of(1893, Month.AUGUST, 20),
      listOf("Eidgenössische Volksinitiative 'für ein Verbot des Schlachtens ohne vorherige Betäubung'")
    )

    given()
      .body(volksabstimmung)
      .contentType(ContentType.JSON)
      .`when`()
      .post()
      .then()
      .statusCode(HttpStatus.SC_CREATED)
  }

  @Test
  fun elections_noElections_resultEmpty() {
    given()
      .`when`()
      .get()
      .then()
      .statusCode(HttpStatus.SC_OK)
      .body(Matchers.`is`("[]"))
  }
}