package importer

import com.melonbase.microquark.rest.dto.inbound.NeueVolksabstimmung
import io.restassured.RestAssured.given
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Collectors
import javax.ws.rs.core.MediaType

/*
 * Helper tool to import Volksabstimmungen from the CSV file 'data.csv'.
 * Furthermore, for every Volksabstimmung the voting is executed.
 *
 * This way you easily get some data into your MicroStream storage!
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

      val volksabstimmung =
        NeueVolksabstimmung(localDate, vorlagen)

      println(volksabstimmung)

      add(volksabstimmung)
      abstimmen(volksabstimmung)
    }
  }

  private fun add(volksabstimmung: NeueVolksabstimmung) {
    given()
      .body(volksabstimmung)
      .contentType(MediaType.APPLICATION_JSON)
      .`when`()
      .post("http://localhost:8080/volksabstimmungen")
  }

  private fun abstimmen(volksabstimmung: NeueVolksabstimmung) {
    given()
      .`when`()
      .post("http://localhost:8080/volksabstimmungen/${volksabstimmung.datum}/abstimmen")
  }
}

private fun List<String>.toPair(): Pair<String, String> {
  return Pair(this[0], this[1])
}
