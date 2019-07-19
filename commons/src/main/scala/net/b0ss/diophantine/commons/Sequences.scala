package net.b0ss.diophantine.commons

import scala.collection.Iterator.iterate

/**
  * Bunch of sequences I might need to use.
  */
object Sequences {

  def positives: Iterator[BigInt] = iterate[BigInt](1)(_ + 1)

  private def pairsWithSum(n: BigInt) = for (x <- n.to(0, -1)) yield (x, n - x)

  private def triplesWithSum(n: BigInt) =
    for (x <- n.to(0, -1); (y, z) <- pairsWithSum(n - x)) yield (x, y, z)

  private def plusMinus(n: BigInt): Seq[BigInt] = if (n == 0) Vector(0) else Vector(-n, n)

  def nCubed = positives flatMap triplesWithSum

  def zCubed =
    for ((x, y, z) <- nCubed; a <- plusMinus(x); b <- plusMinus(y); c <- plusMinus(z))
      yield (a, b, c)

}
