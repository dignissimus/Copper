package me.ezeh.copper.lang

class CopperReturn(val returnExpression: CopperExpression) : CopperValue() {
    override val value: Any
        get() = returnExpression.evaluate().asValue()

    override fun evaluate(): CopperExpression {
        return returnExpression.evaluate()
    }
}