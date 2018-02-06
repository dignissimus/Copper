package me.ezeh.copper.lang

class CopperProgramme(val info: CopperInfo, val listeners: List<CopperListener>, val expressions: List<CopperExpression>) : CopperExpression {
    val environment = CopperEnvironment()

    init {
        for (listener in listeners) {
            // TODO: 'Register' listeners
        }
    }

    fun execute(): CopperExpression {
        for (expression in expressions) {
            expression.evaluate()
        }
        return this
    }

}

