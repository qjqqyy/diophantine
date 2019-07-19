package net.b0ss.diophantine.slave.solver

trait Parametrised[Raw, Equation] {

  def fromRaw(raw: Raw): Equation

}
