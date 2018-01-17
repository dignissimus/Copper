package me.ezeh.copper.lang

class CopperInfo(override val value: Map<String, CopperExpression>) : CopperValue() {
    override fun evaluate() = this
}