package me.ezeh.copper.lang

class CopperVariable(val name: String, private val environment: CopperEnvironment) : CopperExpression {
    override fun evaluate(): CopperExpression = environment.getVariable(name).asValue()
}