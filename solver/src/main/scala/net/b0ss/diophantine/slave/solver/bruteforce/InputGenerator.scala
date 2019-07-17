package net.b0ss.diophantine.slave.solver.bruteforce

import net.b0ss.diophantine.slave.solver.Evaluable

trait InputGenerator[In, Equation <: Evaluable[In]] {

  def inputs(): Iterator[In]

  def find(p: In => Boolean): In = inputs().find(p).get

}
