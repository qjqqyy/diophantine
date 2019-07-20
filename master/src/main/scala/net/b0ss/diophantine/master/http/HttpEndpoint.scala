package net.b0ss.diophantine.master.http

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.{ HttpApp, Route }
import net.b0ss.diophantine.commons.config.TcpConfig

case class HttpEndpoint(it: Iterator[(Array[Byte], String)]) extends HttpApp with TcpConfig {

  def routes: Route =
    (path(token) & get & optionalHeaderValueByName("X-Slave-Identifier") & extractLog) {
      (slaveId, log) =>
        val (bytes, str) = this.synchronized(it.next())
        log.info(s"dispatch $str to $slaveId")

        complete(HttpEntity(ContentTypes.`application/octet-stream`, bytes))
    }

}
