package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidMethodName

class CopperMethodCall(val methodName: String, val arguments: Array<CopperExpression>, val programme: CopperProgramme) : CopperExpression {
    override fun evaluate(): CopperExpression {
        return programme.environment.getMethod(methodName).call(*arguments)
    }

    constructor(methodName: String, arguments: List<CopperExpression>, programme: CopperProgramme) : this(methodName, arguments.toTypedArray(), programme)
}