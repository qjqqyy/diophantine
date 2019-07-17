package net.b0ss.diophantine.commons

object CubelessNatsIterator {

  def it: Iterator[BigInt] = new Iterator[BigInt] {

    val nats = Sequences.positives

    def hasNext = true

    var nextN: BigInt = 1
    var nextNCube: BigInt = 1

    def next(): BigInt = {
      var x = nats.next()
      if (x == nextNCube) {
        nextN += 1
        nextNCube = nextN pow 3
        x = nats.next()
      }
      x
    }

  }
}
