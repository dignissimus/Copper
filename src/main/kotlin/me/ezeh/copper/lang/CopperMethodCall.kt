package me.ezeh.copper.lang

class CopperMethodCall(val methodName: String, val arguments: List<CopperExpression>, val environment: CopperEnvironment) : CopperExpression {
    override fun evaluate(): CopperExpression {
        return environment.getMethod(methodName).call(*(arguments.map { it.evaluate().asValue() }.toTypedArray()))
    }

    constructor(methodName: String, arguments: Array<CopperExpression>, environment: CopperEnvironment) : this(methodName, arguments.toList(), environment)
}