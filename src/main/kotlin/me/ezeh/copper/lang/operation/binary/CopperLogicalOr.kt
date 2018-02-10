package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperLogicalOr(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("logicalOr", programme, left, right)