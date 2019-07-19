# Diophantine

Stupidly bruteforces Diophantine equations.

## Motivation

The author wishes to find more solutions for the Diophantine equation given by
```
x^3 + y^3 n + z^3 n^2 - 3nxyz = 1
```
where `n` is not a cube root.
It is also known as the Cubic Pell's equation due to its relation to the norm of cubic fields.
Note that 富田清二 has already computed
[all solutions till 99](http://www.maroon.dti.ne.jp/fermat/dioph125e.html).

## Background

This project targets the NUS SoC compute cluster. It is characterised by the following.

* It's big, like 100~ish nodes with 20+ CPU cores each.
* I have no access and can't install stuff easily.
* Results can be persisted to a nfs share without any need for synchronisation.

## Overview

There are 3 components.

- **Master**. A minimal networked endpoint which supplies work. Since all work is agreed beforehand all it has to do is to supply `n`.
- **Slave** has 2 parts,
    - **Solver**. Stupidly solves.
    - **Client**. Runs on each node, talks to master and keep multiple slaves alive.

## Protocol (It's shit)

1. Slave connects to master
2. Slave asks for a number by ~~sending a line that~~ doing a HTTP GET on exactly ~~matches~~ `/token`.
3. Master replies with parameters used to construct the parametrised equation.

