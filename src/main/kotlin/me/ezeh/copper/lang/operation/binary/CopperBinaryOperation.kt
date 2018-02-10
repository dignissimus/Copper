package me.ezeh.copper.lang.operation.binary

import me.ezeh.copper.lang.CopperExpression
import me.ezeh.copper.lang.CopperProgramme
import me.ezeh.copper.lang.operation.CopperOperation

abstract class CopperBinaryOperation(val name: String, val programme: CopperProgramme, val left: CopperExpression, val right: CopperExpression) : CopperOperation {
    override fun evaluate(): CopperExpression {
        TODO()
    }
}