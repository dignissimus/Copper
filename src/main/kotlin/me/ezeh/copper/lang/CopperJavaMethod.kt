package me.ezeh.copper.lang

import java.lang.reflect.Method

class CopperJavaMethod(val methods: List<Method>, val javaObject: Any? = null) : CopperMethod(methods[0].name) { // TODO: when methods is empty, error
    // TODO: check that all the method names are the same1

    override fun call(vararg parameters: CopperValue): CopperValue {
        val method: Method
        method = if (methods.size == 1)
            methods[0]
        else {
            val filtered = methods.filter { it.parameterCount == parameters.size }
            filtered[0] // TODO: Do checks
        }
        return CopperJavaObject(method.invoke(javaObject, *(parameters.map { CopperJavaObject(it).toCopper() }).toTypedArray()))
    }

    constructor(method: Method) : this(listOf(method))
}