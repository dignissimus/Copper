package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidArgumentSizeException

class CopperMethodDeclaration(name: String, val args: Array<CopperParameter>, val expressions: Array<CopperExpression>, val programme: CopperProgramme) : CopperMethod(name) {
    override fun call(vararg parameters: CopperValue): CopperValue {
        var returnVal: CopperExpression = CopperStatus.FAILURE
        if (parameters.size != args.size) {
            throw InvalidArgumentSizeException(name, args.size, parameters.size)
        }
        for ((arg, param) in args.zip(parameters)) {
            programme.environment.setVariable(arg.name, param)
        }
        for (expression in expressions) {
            val end = expression.evaluate()
            if (end is CopperReturn) {
                returnVal = end
            }
        }

        return returnVal.asValue()
    }

    constructor(name: String, args: List<CopperParameter>, expressions: List<CopperExpression>, programme: CopperProgramme) : this(name, args.toTypedArray(), expressions.toTypedArray(), programme)
}

