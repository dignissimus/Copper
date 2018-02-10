package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme

class CopperModulus(programme: CopperProgramme, left: CopperExpression, right: CopperExpression) : CopperBinaryOperation("modulus", programme, left, right)