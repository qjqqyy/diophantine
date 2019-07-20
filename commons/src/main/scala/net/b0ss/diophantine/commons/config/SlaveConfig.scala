package net.b0ss.diophantine.commons.config

import java.net.InetAddress

import net.ceedubs.ficus.Ficus._

trait SlaveConfig extends TcpConfig {

  val host = config.as[String]("master-host")

  val resultsSaveDir = config.as[String]("result-save-dir")

  val identifier = config.getAs[String]("slave-identifier") getOrElse {
    InetAddress.getLocalHost.getHostName.split('.')(0)
  }

}
