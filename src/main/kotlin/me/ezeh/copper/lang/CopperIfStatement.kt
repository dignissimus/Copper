package me.ezeh.copper.lang

class CopperIfStatement(val condition: CopperExpression, val ifTrue: List<CopperExpression>, val elseStatement: CopperElseStatement?) : CopperExpression {
    override fun evaluate(): CopperExpression {
        val expressions = if (condition.isTrue()) ifTrue else elseStatement?.expressions ?: emptyList()
        var last: CopperExpression = CopperStatus.FAILURE
        for (expression in expressions) {
            last = expression.evaluate()
        }
        return last
    }

    constructor(condition: CopperExpression, ifTrue: List<CopperExpression>) : this(condition, ifTrue, null)
}