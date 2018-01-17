package me.ezeh.copper.lang

class CopperReturn(override val value: CopperExpression) : CopperValue() { // TODO: expression?
    override fun evaluate(): CopperExpression {
        return this
    }


}