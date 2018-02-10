package me.ezeh.copper.lang.operation.unary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperNegation(programme: CopperProgramme, operand: CopperExpression) : CopperUnaryOperation("negate", programme, operand)