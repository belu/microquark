package com.melonbase.microquark.client

import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import io.restassured.RestAssured.given
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Collectors
import javax.ws.rs.core.MediaType

object RestClient {

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

  private fun abstimmen(volksabstimmung: VolksabstimmungDto) {
    given()
      .`when`()
      .post("http://localhost:8080/volksabstimmungen/${volksabstimmung.datum}/abstimmen")
  }

  private fun add(volksabstimmung: VolksabstimmungDto) {
    given()
      .body(volksabstimmung)
      .contentType(MediaType.APPLICATION_JSON)
      .`when`()
      .post("http://localhost:8080/volksabstimmungen")
  }
}

private fun List<String>.toPair(): Pair<String, String> {
  return Pair(this[0], this[1])
}
