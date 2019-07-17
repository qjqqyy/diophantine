package net.b0ss.diophantine.slave.solver

trait Solvable[In, Equation <: Evaluable[In]] {

  def solve(equation: Equation): In

}
