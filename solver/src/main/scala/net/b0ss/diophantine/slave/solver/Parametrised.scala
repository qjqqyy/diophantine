package net.b0ss.diophantine.slave.solver

trait Parametrised[Equation] {

  def fromArgs(args: Seq[String]): Equation

}
