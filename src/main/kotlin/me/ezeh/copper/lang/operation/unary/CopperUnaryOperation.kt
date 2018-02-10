package me.ezeh.copper.lang.operation.unary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme
import me.ezeh.copper.lang.operation.CopperOperation

abstract class CopperUnaryOperation(val name: String, val programme: CopperProgramme, val operand: CopperExpression) : CopperOperation