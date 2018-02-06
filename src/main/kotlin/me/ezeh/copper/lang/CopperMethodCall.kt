package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidMethodName

class CopperMethodCall(val methodName: String, val arguments: List<CopperExpression>, val programme: CopperProgramme) : CopperExpression {
    override fun evaluate(): CopperExpression {
        return programme.environment.getMethod(methodName).call(*(arguments.map { it.evaluate().asValue() }.toTypedArray()))
    }

    constructor(methodName: String, arguments: Array<CopperExpression>, programme: CopperProgramme) : this(methodName, arguments.toList(), programme)
}