package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidArgumentSizeException

class CopperMethodDeclaration(name: String, val args: Array<CopperParameter>, val expressions: Array<CopperExpression>, val programme: CopperProgramme) : CopperMethod(name) {
    override fun call(vararg parameters: CopperValue): CopperValue {
        if (parameters.size != args.size) {
            throw InvalidArgumentSizeException(name, args.size, parameters.size)
        }
        for ((arg, param) in args.zip(parameters)) {
            programme.environment.setVariable(arg.name, param) // TODO: 'unset' parameters after execution
        }
        for (expression in expressions)
            if (expression is CopperReturn)
                return expression.evaluate().asValue()
            else
                expression.evaluate()

        return CopperStatus.FAILURE
    }

    constructor(name: String, args: List<CopperParameter>, expressions: List<CopperExpression>, programme: CopperProgramme) : this(name, args.toTypedArray(), expressions.toTypedArray(), programme)
}

