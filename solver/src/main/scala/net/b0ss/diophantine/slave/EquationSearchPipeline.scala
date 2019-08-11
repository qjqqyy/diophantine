package net.b0ss.diophantine.slave

import java.io.{ File, FileWriter }

import net.b0ss.diophantine.commons.config.SlaveConfig
import net.b0ss.diophantine.slave.solver.{ Evaluable, Parametrised, Solvable }

import scala.annotation.tailrec

abstract class EquationSearchPipeline[Raw, In, Equation <: Evaluable[In]](
  implicit serializableEvidence: Parametrised[Raw, Equation],
  solvableEvidence: Solvable[In, Equation],
) extends Runnable
    with SlaveConfig {

  def nextParam(): Option[Raw]

  private def nextEquation(): Option[Equation] =
    nextParam().map(serializableEvidence.fromRaw) flatMap { equation =>
      if (new File(s"$resultsSaveDir/$equation").exists) nextEquation() else Some(equation)
    }

  private def processSolution(equation: Equation, ans: In): Unit = {
    val fw = new FileWriter(s"$resultsSaveDir/$equation")
    fw.write(s"$ans\n")
    fw.close()
  }

  @tailrec
  final def run(): Unit =
    nextEquation().map(solvableEvidence.solveWithoutDiscardingEquation) match {
      case None => ()

      case Some((eq, ans)) =>
        processSolution(eq, ans)
        run()
    }

}
