package me.ezeh.copper.lang

abstract class CopperMethod(val name: String) : CopperExpression {
    abstract fun call(vararg parameters: CopperValue): CopperValue
}