package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperLogicalAnd(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("logicalAnd", programme, left, right)