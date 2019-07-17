package net.b0ss.diophantine.commons.config

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._

trait TcpConfig {

  val config = ConfigFactory.load()

  val port = config.as[Int]("master-port")

  val token = config.as[String]("token-command")

}
