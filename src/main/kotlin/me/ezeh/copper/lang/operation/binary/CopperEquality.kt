package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperEquality(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("equality", programme, left, right)