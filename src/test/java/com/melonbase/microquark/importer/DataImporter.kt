package com.melonbase.microquark.importer

import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import io.restassured.RestAssured.given
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Collectors
import javax.ws.rs.core.MediaType

/*
 * Helper class to import Volksabstimmungen from file 'data.csv'.
 * Furthermore, for every Volksabstimmung the voting (=abstimmen)
 * is peformed.
 *
 * This lets you fill your MicroStream storage with ease.
 *
 * The data has been taken from:
 * https://www.bk.admin.ch/ch/d/pore/va/vab_2_2_4_1_gesamt.html
 */
object DataImporter {

  @JvmStatic
  fun main(args: Array<String>) {
    val vorlagenByDate = Files.lines(Path.of("data.csv"))
      .filter { line -> line.isNotEmpty() }
      .map { it.split(';').toPair() }
      .collect(Collectors.groupingBy { it.first })

    vorlagenByDate.forEach { (datum, pairs) ->
      val localDate = LocalDate.parse(datum)
      val vorlagen = pairs.map { it.second }

      val volksabstimmung = VolksabstimmungDto(localDate, vorlagen)

      println(volksabstimmung)

      add(volksabstimmung)
      abstimmen(volksabstimmung)
    }
  }

  private fun add(volksabstimmung: VolksabstimmungDto) {
    given()
      .body(volksabstimmung)
      .contentType(MediaType.APPLICATION_JSON)
      .`when`()
      .post("http://localhost:8080/volksabstimmungen")
  }

  private fun abstimmen(volksabstimmung: VolksabstimmungDto) {
    given()
      .`when`()
      .post("http://localhost:8080/volksabstimmungen/${volksabstimmung.datum}/abstimmen")
  }
}

private fun List<String>.toPair(): Pair<String, String> {
  return Pair(this[0], this[1])
}
