package net.b0ss.diophantine.commons.config

import net.ceedubs.ficus.Ficus._

trait SlaveConfig extends TcpConfig {

  val host = config.as[String]("master-host")

  val resultsSaveDir = config.as[String]("result-save-dir")

}
