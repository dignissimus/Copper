package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidMethodName
import me.ezeh.copper.exception.InvalidVariableName
import me.ezeh.copper.exception.MethodAlreadyExistsException
import me.ezeh.copper.lang.methods.PrintMethod

class CopperEnvironment {
    private val methods: MutableMap<String, CopperMethod> = mutableMapOf("print" to PrintMethod())
    private val variables: MutableMap<String, CopperValue> = mutableMapOf()

    fun getMethod(methodName: String): CopperMethod {
        val matches = methods.toList().filter { it.second.name.toLowerCase() == methodName.toLowerCase() }

        if (matches.isEmpty())
            throw InvalidMethodName(methodName)

        assert(matches.size == 1) // assert there is only one match // TODO: assert?
        val match = matches[0]
        if (match.first != methodName) {
            // If name is not exactly the same // TODO: warning?
        }
        return match.second

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

    fun hasMethod(methodName: String): Boolean = !methods.filter { it.key.toLowerCase() == methodName.toLowerCase() }.isEmpty()

    fun setVariable(varName: String, value: CopperValue) {
        if (hasVariable(varName)) {
            val matches = variables.toList().filter { it.first.toLowerCase() == varName.toLowerCase() }
            val match = matches[0]
            variables[match.first] = value
        }
        else
            variables[varName] = value
    }

    fun setVariable(variable: CopperVariable, value: CopperValue) = setVariable(variable.name, value)

    fun removeVariable(variable: String) {
        variables.remove(variable)
    }

    fun removeVariable(variable: CopperVariable) = removeVariable(variable.name)

    fun getVariable(varName: String): CopperValue {
        if (varName.split(".").size > 1) {
            val split = varName.split(".")
            var variable: CopperValue? = null
            var index = 0
            while (index < split.size) {
                if (variable == null)
                    variable = getVariable(split[index++])
                variable = (variable as? CopperObject ?: break).getVariable(split[index])
            }
            if (variable != null) {
                return variable
            }
        }
        if (!hasVariable(varName))
            throw InvalidVariableName(varName)

        val matches = variables.toList().filter { it.first.toLowerCase() == varName.toLowerCase() }
        val match = matches[0]

        if (match.first != varName) { // If variable name is not exactly the same
            // TODO: warning?
        }

        return match.second

    }

    fun hasVariable(varName: String) = !variables.filter { it.key.toLowerCase() == varName.toLowerCase() }.isEmpty()

}