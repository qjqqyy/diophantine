package net.b0ss.diophantine.master.http

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.{ HttpApp, Route }
import net.b0ss.diophantine.commons.config.TcpConfig

case class HttpEndpoint(it: Iterator[Array[Byte]]) extends HttpApp with TcpConfig {
  def routes: Route =
    path(token) {
      get {
        val x: Array[Byte] = this.synchronized { it.next() }
        complete(HttpEntity(ContentTypes.`application/octet-stream`, x))
      }
    }
}
