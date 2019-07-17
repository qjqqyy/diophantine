package net.b0ss.diophantine.slave.solver.equations

import net.b0ss.diophantine.commons.Sequences
import net.b0ss.diophantine.slave.solver.bruteforce.{ BruteForceSolver, InputGenerator }
import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

/**
  * Expresses the cubic Pell's equation {{{x^3 + y^3 n + z^3 n^2 - 3nxyz = 1}}}.
  */
case class CubicPell(n: BigInt) extends Evaluable[(BigInt, BigInt, BigInt)] {

  override def toString = s"x^3 + y^3 $n + z^3 $n^2 - 3xyz $n = 1"

  val evaluate = {
    case (x, y, z) =>
      (x pow 3) + (y pow 3) * n + (z pow 3) * (n pow 2) - 3 * n * x * y * z - 1
  }

}

object CubicPell {

  private[solver] def bruteForceInputs: InputGenerator[(BigInt, BigInt, BigInt), CubicPell] =
    () => Sequences.zCubed.drop(2) // we ignore the trivial solution (1,0,0)

  private[solver] implicit def parseParameters: Parametrised[CubicPell] = {
    case (n: String) +: _ => CubicPell(BigInt(n))
  }

  private[solver] implicit def solvableCubicPell: Solvable[(BigInt, BigInt, BigInt), CubicPell] =
    BruteForceSolver(bruteForceInputs)(_).solve

}
