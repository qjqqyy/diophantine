package net.b0ss.diophantine.master.tcpserver

import net.b0ss.diophantine.commons.CubelessNatsIterator

object CubelessTcpServer extends TcpServer(CubelessNatsIterator.it.map(_.toString))
