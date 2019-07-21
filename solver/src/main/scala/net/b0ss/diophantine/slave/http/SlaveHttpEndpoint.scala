package net.b0ss.diophantine.slave.http

import com.softwaremill.sttp._
import net.b0ss.diophantine.commons.config.SlaveConfig

import scala.concurrent.duration._

object SlaveHttpEndpoint extends SlaveConfig {
  implicit val backend = HttpURLConnectionBackend(
    options = SttpBackendOptions.connectionTimeout(10.seconds)
  )

  def nextParam() =
    sttp
      .followRedirects(false)
      .get(Uri(host, port, Vector(token)))
      .header("X-Slave-Identifier", identifier)
      .response(asByteArray)
      .send()
      .body
      .toOption
}
