package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperDivision(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("divide", programme, left, right)