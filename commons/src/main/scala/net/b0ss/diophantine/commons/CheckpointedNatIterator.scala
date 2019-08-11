package net.b0ss.diophantine.commons

import java.io.{ BufferedWriter, FileWriter }

import scala.io.Source
import scala.util.Try

class CheckpointedNatIterator(checkpointFile: String) extends Iterator[BigInt] {

  private def saveCheckpointFile(): Unit = {
    val bw = new BufferedWriter(new FileWriter(checkpointFile))
    acc.toByteArray.foreach(bw.write(_))
  }

  private def readCheckpointFile(): Try[Array[Byte]] = Try(Source.fromFile(checkpointFile).toArray)

  private def isCheckpoint(n: BigInt): Boolean = (n & 31) == 0

  private var acc: BigInt = readCheckpointFile().map(BigInt(_)) getOrElse 0

  def hasNext = true

  def next(): BigInt = {
    acc += 1
    if (isCheckpoint(acc)) saveCheckpointFile()
    acc
  }

}
