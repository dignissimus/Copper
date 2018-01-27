package me.ezeh.copper.lang

class CopperVariable(val name: String, val value: CopperExpression?) : CopperExpression {
    constructor(name: String) : this(name, null)
}