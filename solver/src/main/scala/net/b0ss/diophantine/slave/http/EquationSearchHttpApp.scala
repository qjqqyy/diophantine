package net.b0ss.diophantine.slave.http

import java.io.{ BufferedWriter, FileWriter }

import net.b0ss.diophantine.commons.config.SlaveConfig
import net.b0ss.diophantine.slave.EquationSearchPipeline
import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

abstract class EquationSearchHttpAppWorker[In, Equation <: Evaluable[In]](
  implicit serializableEvidence: Parametrised[Array[Byte], Equation],
  eqnSolvableEvidence: Solvable[In, Equation],
) extends EquationSearchPipeline[Array[Byte], In, Equation]
    with SlaveConfig {

  def nextParam() = SlaveHttpEndpoint.nextParam()

  def processSolution(equation: Equation, ans: In): Unit = {
    // TODO: consider better file names
    val bw = new BufferedWriter(new FileWriter(s"$resultsSaveDir/$equation"))
    bw.write(s"$ans\n")
    bw.close()
  }

}

abstract class EquationSearchHttpApp[In, Equation <: Evaluable[In]](
  worker: EquationSearchHttpAppWorker[In, Equation]
) extends App {

  val cores = Runtime.getRuntime.availableProcessors

  1 to cores foreach { _ =>
    new Thread(worker).start()
  }
}
