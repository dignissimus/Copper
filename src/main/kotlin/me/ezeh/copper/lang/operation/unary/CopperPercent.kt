package me.ezeh.copper.lang.operation.unary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperPercent(programme: CopperProgramme, operand: CopperExpression) : CopperUnaryOperation("percent", programme, operand)