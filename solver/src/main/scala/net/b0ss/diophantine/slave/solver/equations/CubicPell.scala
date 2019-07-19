package net.b0ss.diophantine.slave.solver.equations

import net.b0ss.diophantine.commons.Sequences
import net.b0ss.diophantine.slave.solver.bruteforce.{ BruteForceSolver, InputGenerator }
import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

/**
  * Expresses the cubic Pell's equation {{{x^3 + y^3 n + z^3 n^2 - 3nxyz = 1}}}.
  */
case class CubicPell(n: BigInt) extends Evaluable[(BigInt, BigInt, BigInt)] {

  //override def toString = s"x^3 + y^3 $n + z^3 $n^2 - 3xyz $n = 1"
  override def toString = n.toString

  val evaluate = {
    case (x, y, z) =>
      (x pow 3) + (y pow 3) * n + (z pow 3) * (n pow 2) - 3 * n * x * y * z - 1
  }

}

object CubicPell {

  private def bruteForceInputs: InputGenerator[(BigInt, BigInt, BigInt), CubicPell] =
    () => Sequences.zCubed.drop(2) // we ignore the trivial solution (1,0,0)

  private[slave] implicit def solvableCubicPell: Solvable[(BigInt, BigInt, BigInt), CubicPell] =
    BruteForceSolver(bruteForceInputs)(_).solve

  /** Directly read Byte Array into BigInt. */
  private[slave] implicit def cubicPellFromBytes: Parametrised[Array[Byte], CubicPell] =
    bytes => CubicPell(BigInt(bytes))

  /** This is mainly for interactive use. */
  private[slave] implicit def cubicPellFromString: Parametrised[String, CubicPell] =
    s => CubicPell(BigInt(s))

}
