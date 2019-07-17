package net.b0ss.diophantine.slave.solver.bruteforce

import net.b0ss.diophantine.slave.solver.Evaluable

case class BruteForceSolver[In, Equation <: Evaluable[In]](
  inputGenerator: InputGenerator[In, Equation]
)(equation: Equation) {

  def solve: In = inputGenerator.find(equation.evaluate(_) == BigInt(0))

}
