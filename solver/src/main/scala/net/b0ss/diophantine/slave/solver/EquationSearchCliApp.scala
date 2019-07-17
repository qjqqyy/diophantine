package net.b0ss.diophantine.slave.solver

abstract class EquationSearchCliApp[In, Equation <: Evaluable[In]](
  implicit parametrisedEquation: Parametrised[Equation],
  eqnSolvableEvidence: Solvable[In, Equation]
) extends App {

  val params = args.toIndexedSeq.dropWhile(!_.forall(Character.isDigit))
  val equation = parametrisedEquation.fromArgs(params)

  println(equation)
  println(eqnSolvableEvidence.solve(equation))
}
