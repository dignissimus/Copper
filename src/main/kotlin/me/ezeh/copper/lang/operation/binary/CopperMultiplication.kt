package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperMultiplication(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("multiply", programme, left, right)