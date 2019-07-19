package net.b0ss.diophantine.slave.http

import net.b0ss.diophantine.slave.solver.equations.CubicPell

object CubicPellWorker extends EquationSearchHttpAppWorker[(BigInt, BigInt, BigInt), CubicPell]

object CubicPellSearchHttpApp extends EquationSearchHttpApp(CubicPellWorker)
