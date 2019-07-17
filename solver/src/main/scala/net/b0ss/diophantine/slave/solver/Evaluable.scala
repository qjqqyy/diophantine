package net.b0ss.diophantine.slave.solver

trait Evaluable[Input] {

  def evaluate: Input => BigInt

}
