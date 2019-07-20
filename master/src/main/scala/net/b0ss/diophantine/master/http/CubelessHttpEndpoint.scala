package net.b0ss.diophantine.master.http

import net.b0ss.diophantine.commons.CubelessNatsIterator
import net.b0ss.diophantine.commons.config.TcpConfig

object CubelessHttpEndpoint extends App with TcpConfig {
  HttpEndpoint(CubelessNatsIterator().map { x =>
    (x.toByteArray, x.toString)
  }).startServer("0.0.0.0", port)
}
