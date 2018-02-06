package me.ezeh.copper.lang

class CopperProgramme : CopperExpression {
    // TODO: does this have to be lateinit?
    lateinit var info: CopperInfo
    val listeners = mutableListOf<CopperListener>()
    val expressions = mutableListOf<CopperExpression>()
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

    fun addExpression(expression: CopperExpression) {
        expressions.add(expression)
    }

    fun addListener(listener: CopperListener) {
        listeners.add(listener)
    }

}

