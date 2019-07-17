package net.b0ss.diophantine.master.tcpserver

import java.net.InetSocketAddress

import akka.actor.{ Actor, ActorLogging, ActorSystem, Props }
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import net.b0ss.diophantine.commons.config.TcpConfig

private class Server(port: Int, token: ByteString, outs: Iterator[String])
    extends Actor
    with ActorLogging {
  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress(port))

  def receive: Receive = {
    case b @ Bound(local) =>
      log.info(s"Server started on $local")
      context.parent ! b

    case CommandFailed(_: Bind) =>
      log.error("Failed to bind! Stopping...")
      context stop self

    case Connected(remote, local) =>
      val handler = context.actorOf(Props(classOf[Handler], token, outs))
      log.info(s"New connnection: $local <- $remote")
      sender() ! Register(handler)
  }
}

private class Handler(token: ByteString, outs: Iterator[String]) extends Actor with ActorLogging {
  import Tcp._

  def receive: Actor.Receive = {
    case PeerClosed => context stop self

    case Received(data) if data == token =>
      sender() ! Write(ByteString(outs.next()) ++ ByteString("\r\n"))
  }
}

/**
  * Not even an echo server, it's more useless than that.
  */
abstract class TcpServer(outputSequence: Iterator[String]) extends TcpConfig with App {

  val actorSystem = ActorSystem()

  val tcpServer =
    actorSystem.actorOf(Props(classOf[Server], port, ByteString(token ++ "\r\n"), outputSequence))

}
