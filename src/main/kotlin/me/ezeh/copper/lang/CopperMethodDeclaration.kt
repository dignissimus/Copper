package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidArgumentSizeException

class CopperMethodDeclaration(val name: String, val args: Array<CopperParameter>, val expressions: Array<CopperExpression>, val programme: CopperProgramme) : CopperExpression {
    fun call(vararg parameters: CopperExpression): CopperExpression {
        var returnVal: CopperExpression = CopperNull.instance
        if (parameters.size != args.size) {
            throw InvalidArgumentSizeException(name, args.size, parameters.size)
        }
        for ((arg, param) in args.zip(parameters)) {
            programme.environment.setVariable(arg.name, param.evaluate().asValue())
        }
        for (expression in expressions) {
            val end = expression.evaluate()
            if (end is CopperReturn) {
                returnVal = end
            }
        }

        return returnVal
    }

    constructor(name: String, args: List<CopperParameter>, expressions: List<CopperExpression>, programme: CopperProgramme) : this(name, args.toTypedArray(), expressions.toTypedArray(), programme)
}

