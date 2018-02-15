package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidMethodName
import me.ezeh.copper.exception.InvalidVariableName
import me.ezeh.copper.exception.MethodAlreadyExistsException
import me.ezeh.copper.lang.methods.PrintFormat
import me.ezeh.copper.lang.methods.PrintMethod

class CopperEnvironment { // TODO: merge with CopperObject?
    private val methods: MutableMap<String, CopperMethod> = mutableMapOf("print" to PrintMethod(), "printf" to PrintFormat())
    private val variables: MutableMap<String, CopperValue> = mutableMapOf()

    fun getMethod(methodName: String): CopperMethod {
        val split = methodName.split(".")
        if (split.size > 1) {
            val methodName = split.last()
            val varName = split.dropLast(1).joinToString(".")
            return (getVariable(varName) as CopperObject).getMethod(methodName)
        }
        return methods[methodName] ?: throw InvalidMethodName(methodName)

    }

    fun addMethod(methodName: String, method: CopperMethod) {
        if (hasMethod(methodName))
            throw MethodAlreadyExistsException(methodName)
        methods[methodName] = method
    }

    fun addMethod(method: CopperMethod) = addMethod(method.name, method)

    fun removeMethod(methodName: String) {
        methods.remove(methodName)
    }

    fun hasMethod(methodName: String) = methods.containsKey(methodName)

    fun setVariable(varName: String, value: CopperValue) {
        variables[varName] = value
    }

    fun setVariable(variable: CopperVariable, value: CopperValue) = setVariable(variable.name, value)

    fun removeVariable(variable: String) {
        variables.remove(variable.toLowerCase())
    }

    fun removeVariable(variable: CopperVariable) = removeVariable(variable.name)

    fun getVariable(varName: String): CopperValue {
        if (varName.split(".").size > 1) {
            val split = varName.split(".")
            var variable: CopperValue? = null
            var index = 0
            while (index < split.size) {
                variable = (variable as? CopperObject)?.getVariable(split[index++]) ?: getVariable(split[index++])
            }
            if (variable != null) {
                return variable
            }
        }
        if (!hasVariable(varName))
            throw InvalidVariableName(varName)

        return variables[varName] ?: throw InvalidVariableName(varName)
    }

    fun hasVariable(varName: String) = variables.containsKey(varName)

}