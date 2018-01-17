package me.ezeh.copper.lang

class CopperNull : CopperExpression {
    companion object {
        val instance = CopperNull()
    }

    override fun evaluate(): CopperExpression {
        return this
    }
}