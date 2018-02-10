package me.ezeh.copper.lang.operation.unary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperFactorial(programme: CopperProgramme, operand: CopperExpression) : CopperUnaryOperation("logicalNot", programme, operand)