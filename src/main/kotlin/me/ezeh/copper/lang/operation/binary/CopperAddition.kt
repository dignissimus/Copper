package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperAddition(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("add", programme, left, right)

