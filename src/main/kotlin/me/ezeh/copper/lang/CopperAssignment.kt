package me.ezeh.copper.lang

class CopperAssignment(private val variable: CopperVariable, private val variableValue: CopperExpression, private val environment: CopperEnvironment) : CopperValue() {
    override val value: Any
        get() = evaluate().asValue()

    override fun evaluate(): CopperExpression {
        val evaluated = variableValue.evaluate().asValue()
        environment.setVariable(variable, evaluated)
        return evaluated
    }
}