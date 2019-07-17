package net.b0ss.diophantine.slave.solver.equations

import org.scalatest.FlatSpec
import CubicPell.solvableCubicPell

class CubicPellSpec extends FlatSpec {

  "CubicPell(2)" should "have root (1,1,1)" in {
    assert(CubicPell(2).evaluate(1, 1, 1) == 0)
  }

  "CubicPell(2)" should "not have root (0,0,0)" in {
    assert(CubicPell(2).evaluate(0, 0, 0) != 0)
  }

  "Solving CubicPell(13)" should "find the solution" in {
    assert(solvableCubicPell.solve(CubicPell(13)) == (-4, -3, 2))
  }

}
