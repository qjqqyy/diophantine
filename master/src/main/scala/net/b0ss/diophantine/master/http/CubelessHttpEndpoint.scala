package net.b0ss.diophantine.master.http

import net.b0ss.diophantine.commons.CubelessNatsIterator
import net.b0ss.diophantine.commons.config.TcpConfig

object CubelessHttpEndpoint extends App with TcpConfig {
  HttpEndpoint(CubelessNatsIterator.it.map(_.toByteArray)).startServer("0.0.0.0", port)
}
