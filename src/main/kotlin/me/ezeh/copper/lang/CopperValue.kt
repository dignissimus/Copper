package me.ezeh.copper.lang

abstract class CopperValue : CopperExpression {
    abstract val value: Any
    override fun toString(): String = "${this::class.simpleName}::{$value}"
}