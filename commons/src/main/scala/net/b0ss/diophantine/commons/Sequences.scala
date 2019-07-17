package net.b0ss.diophantine.commons

import scala.collection.Iterator.iterate

/**
  * Bunch of sequences I might need to use.
  */
object Sequences {

  def positives: Iterator[BigInt] = iterate[BigInt](1)(_ + 1)

  private def pairsWithSum(n: BigInt) = n to (0, -1) map (x => (x, n - x))

  private def triplesWithSum(n: BigInt) =
    n to (0, -1) flatMap { x =>
      pairsWithSum(n - x) map { case (y, z) => (x, y, z) }
    }

  private val plusMinusOne = Seq(-1, 1)

  def nCubed = positives flatMap triplesWithSum

  def zCubed = nCubed flatMap {
    case (x, y, z) =>
      for (a <- plusMinusOne if a > 0 || x > 0;
           b <- plusMinusOne if b > 0 || y > 0;
           c <- plusMinusOne if c > 0 || z > 0)
        yield (a * x, b * y, c * z)
  }

}
