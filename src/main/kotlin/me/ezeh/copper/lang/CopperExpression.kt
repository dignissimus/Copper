package me.ezeh.copper.lang

interface CopperExpression {
    fun evaluate() = this
    fun isTrue(): Boolean {
        val evaluated = this.evaluate()
        if (evaluated is CopperBool)
            return evaluated.value

        if (evaluated is CopperStatus)
            return evaluated.value == CopperStatus.Status.SUCCESSFUL

        return false
    }

    fun asValue(): CopperValue = this as? CopperValue ?: throw TODO("Create Exception")

}