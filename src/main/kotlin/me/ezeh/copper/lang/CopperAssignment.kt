package me.ezeh.copper.lang

class CopperAssignment(val variable: CopperVariable, val variableValue: CopperExpression, val programme: CopperProgramme) : CopperValue() {
    override val value: Any
        get() = evaluate().asValue()

    override fun evaluate(): CopperExpression {
        val evaluated = variableValue.evaluate().asValue()
        programme.environment.setVariable(variable, evaluated)
        return evaluated
    }
}