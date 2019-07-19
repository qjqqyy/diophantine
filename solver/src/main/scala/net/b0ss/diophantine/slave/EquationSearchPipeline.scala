package net.b0ss.diophantine.slave

import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

import scala.annotation.tailrec

abstract class EquationSearchPipeline[Raw, In, Equation <: Evaluable[In]](
  implicit serializableEvidence: Parametrised[Raw, Equation],
  solvableEvidence: Solvable[In, Equation],
) extends Runnable {

  def nextParam(): Option[Raw]

  def processSolution(equation: Equation, ans: In): Unit

  @tailrec
  final def run(): Unit = {
    nextParam()
      .map(serializableEvidence.fromRaw)
      .map(solvableEvidence.solveWithoutDiscardingEquation)
      .foreach { (processSolution _).tupled }

    run()
  }

}
