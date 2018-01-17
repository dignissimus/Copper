package me.ezeh.copper.lang

abstract class CopperValue : CopperExpression { // TODO: use? Abstract class?
    abstract val value: Any
    override fun toString(): String = "${this::class.simpleName}::{$value}"
}