package me.ezeh.copper.lang

class CopperVariable(val name: String, val programme: CopperProgramme) : CopperExpression {
    override fun evaluate(): CopperExpression = programme.environment.getVariable(name).asValue()
}