package me.ezeh.copper.lang

class CopperElseStatement(val expressions: List<CopperExpression>) : CopperExpression {
    override fun evaluate(): CopperExpression {
        var last: CopperExpression = CopperNull.instance
        for (expression in expressions) {
            last = expression.evaluate()
        }
        return last
    }
}