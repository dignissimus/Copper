package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidMethodName
import me.ezeh.copper.exception.InvalidVariableName
import me.ezeh.copper.exception.MethodAlreadyExistsException

class CopperEnvironment {
    private val methods: MutableMap<String, CopperMethodDeclaration> = mutableMapOf()
    private val variables: MutableMap<String, CopperValue> = mutableMapOf()

    fun getMethod(methodName: String): CopperMethodDeclaration {
        val matches = methods.toList().filter { it.second.name.toLowerCase() == methodName.toLowerCase() }

        if (matches.isEmpty())
            throw InvalidMethodName(methodName)

        assert(matches.size == 1) // assert there is only one match // TODO: assert?
        val match = matches[0]
        if (match.first != methodName) {
            // If name is not the same // TODO: warning?
        }
        return match.second

    }

    fun addMethod(methodName: String, method: CopperMethodDeclaration) {
        if (hasMethod(methodName))
            throw MethodAlreadyExistsException(methodName)
        methods[methodName] = method
    }

    fun removeMethod(methodName: String) {
        methods.remove(methodName)
    }

    fun hasMethod(methodName: String): Boolean = !methods.filter { it.key.toLowerCase() == methodName.toLowerCase() }.isEmpty()

    fun setVariable(varName: String, value: CopperValue) {
        if (hasVariable(varName)) {
            val matches = variables.toList().filter { it.first.toLowerCase() == varName.toLowerCase() }
            assert(matches.size == 1) // TODO: assert?
            val match = matches[0]
            variables[match.first] = value
        }
        else
            variables[varName] = value


    }

    fun getVariable(varName: String): CopperValue {
        if (!hasVariable(varName))
            throw InvalidVariableName(varName)

        val matches = variables.toList().filter { it.first.toLowerCase() == varName.toLowerCase() }
        assert(matches.size == 1) // TODO: assert?
        val match = matches[0]

        if (match.first != varName) { // If variable name is not exactly the same
            // TODO: warning?
        }

        return match.second

    }

    fun hasVariable(varName: String) = !variables.filter { it.key.toLowerCase() == varName.toLowerCase() }.isEmpty()


}