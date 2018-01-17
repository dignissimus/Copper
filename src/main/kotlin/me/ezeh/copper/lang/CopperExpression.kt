package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidVariableName

interface CopperExpression {
    fun evaluate(): CopperExpression
    fun isTrue(): Boolean {
        val evaluated = this.evaluate()
        if (evaluated is CopperBool && evaluated.value)
            return true

        if (evaluated !is CopperNull)
            return true

        return false
    }

    fun asValue(): CopperValue = this as? CopperValue ?: throw TODO("Create Exception")

}