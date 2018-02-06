package me.ezeh.copper.lang.methods

import me.ezeh.copper.lang.CopperMethod
import me.ezeh.copper.lang.CopperStatus
import me.ezeh.copper.lang.CopperValue

class PrintMethod : CopperMethod("print") {
    override fun call(vararg parameters: CopperValue): CopperValue {
        println(parameters.map { it.value }.joinToString(" "))
        return CopperStatus.SUCCESS
    }
}