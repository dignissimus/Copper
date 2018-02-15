package me.ezeh.copper.lang

abstract class CopperValue : CopperExpression { // TODO: types, deal with 'undecided' and successful/unsuccessful
    abstract val value: Any // TODO: Move to constructor?
    override fun toString(): String = "{type: ${this::class.simpleName}, value: $value"
    open fun toCopper(): Any = (this as? CopperObject)?.toCopper() ?: this
}