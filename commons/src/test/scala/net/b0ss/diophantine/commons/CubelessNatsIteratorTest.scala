package net.b0ss.diophantine.commons

import org.scalatest.FlatSpec

class CubelessNatsIteratorTest extends FlatSpec {
  val first30 = CubelessNatsIterator().take(30).toSet
  val smallCubes = Set[BigInt](1, 8, 27)

  "CubelessNatsIterator" should "not include cubic integers" in {
    assert((first30 intersect smallCubes).isEmpty)
  }

  "CubelessNatsIterator" should "include all other integers" in {
    assert((first30 union smallCubes) == BigInt(1).to(33).toSet)
  }
}
