package net.b0ss.diophantine.slave.http

import net.b0ss.diophantine.commons.config.SlaveConfig
import com.softwaremill.sttp._
import scala.concurrent.duration._

object SlaveHttpEndpoint extends SlaveConfig {
  implicit val backend = TryHttpURLConnectionBackend(
    options = SttpBackendOptions.connectionTimeout(10.seconds)
  )

  def nextParam() =
    sttp
      .followRedirects(false)
      .get(Uri(host, port, Vector(token)))
      .header("X-Slave-Identifier", identifier)
      .send()
      .toEither
      .flatMap(_.body)
      .toOption
      .map(_.getBytes)
}
