package me.ezeh.copper.lang

class CopperVariable(val name: String, val value: CopperExpression?) : CopperExpression {
    override fun evaluate(): CopperExpression {
        return this
    }

    constructor(name: String) : this(name, null)

}