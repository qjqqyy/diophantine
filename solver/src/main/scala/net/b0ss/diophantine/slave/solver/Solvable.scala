package net.b0ss.diophantine.slave.solver

trait Solvable[In, Equation <: Evaluable[In]] {

  def solve(equation: Equation): In

  def solveWithoutDiscardingEquation(e: Equation): (Equation, In) = (e, solve(e))

}
