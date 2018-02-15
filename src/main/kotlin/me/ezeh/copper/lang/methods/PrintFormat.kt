package me.ezeh.copper.lang.methods

import me.ezeh.copper.exception.NotEnoughArgumentsException
import me.ezeh.copper.lang.*

class PrintFormat : CopperMethod("printf") {
    override fun call(vararg parameters: CopperValue): CopperSuccess { // TODO: do own format?
        if (parameters.isEmpty())
            throw NotEnoughArgumentsException(expected = 1, actual = 0)

        val formatString = parameters[0] as? CopperString
                ?: throw IllegalStateException("Expecting a string") // InvalidArgumentTypeException("printd", CopperType.STRING, parameters[0].type) // TODO: when types exist
        val formatted = String.format(formatString.value, *(parameters.drop(1).map { it.value }.toTypedArray()))
        println(formatted)
        return CopperStatus.SUCCESS
    }
}