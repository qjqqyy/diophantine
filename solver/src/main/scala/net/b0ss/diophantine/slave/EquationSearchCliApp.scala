package net.b0ss.diophantine.slave

import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

import scala.io.StdIn

abstract class EquationSearchCliApp[In, Equation <: Evaluable[In]](
  implicit serializableEvidence: Parametrised[String, Equation],
  eqnSolvableEvidence: Solvable[In, Equation],
) extends EquationSearchPipeline[String, In, Equation]
    with App {

  def nextParam() = Option(StdIn.readLine)

  def processSolution(eqn: Equation, ans: In): Unit = println(s"$eqn\t $ans")

  run()

}
