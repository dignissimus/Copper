package me.ezeh.copper.lang

abstract class CopperValue : CopperExpression { // TODO: types, deal with 'undecided' and successful/unsuccessful
    abstract val value: Any // TODO: Move to constructor?
    override fun toString(): String = "${this::class.simpleName}::{$value}"
    open fun toCopper(): Any = (this as? CopperObject)?.toCopper() ?: this
}