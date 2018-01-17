package me.ezeh.copper.lang

class CopperIfStatement(val condition: CopperExpression, val ifTrue: List<CopperExpression>, val elseStatement: CopperElseStatement?) : CopperExpression {
    override fun evaluate(): CopperExpression {
        val expressions = if (condition.isTrue()) ifTrue else elseStatement?.expressions ?: listOf(CopperNull.instance)
        var last: CopperExpression = CopperNull.instance
        for (expression in expressions) {
            last = expression
        }
        return last
    }

    constructor(condition: CopperExpression, ifTrue: List<CopperExpression>) : this(condition, ifTrue, null)
}