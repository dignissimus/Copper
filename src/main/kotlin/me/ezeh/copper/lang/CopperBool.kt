package me.ezeh.copper.lang

class CopperBool(override val value: Boolean) : CopperValue() {
    override fun evaluate(): CopperExpression {
        return this
    }
}