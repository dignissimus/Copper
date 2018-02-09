package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidVariableName

open class CopperJavaObject(private val javaObject: Any) : CopperObject() {
    override val value = javaObject
    private val javaClass = javaObject::class.java

    override fun setVariable(name: String, value: CopperExpression) {
        if (javaClass.methods.map { it.name }.contains("set${name.capitalize()}")) {

        }
        if (javaClass.fields.map { it.name }.contains(name)) {

        }
    }

    override fun getMethod(name: String, value: CopperExpression): CopperMethod {
        return CopperJavaMethod(javaClass.methods.filter { it.name == name })
    }

    override fun getVariable(name: String): CopperJavaObject {
        if (name.split(".").size > 1) {
            var split = name.split(".")
            var variable: CopperJavaObject? = null
            var index = 0
            while (index < split.size) {
                variable = variable?.getVariable(split[index++]) ?: getVariable(split[index++])
            }
            if (variable != null) {
                return variable
            }
        }
        if (javaClass.methods.map { it.name }.contains("get${name.capitalize()}")) {
            return CopperJavaObject(javaClass.getMethod("get${name.capitalize()}").invoke(javaObject))
        }
        if (javaClass.fields.map { it.name }.contains(name)) {
            return CopperJavaObject(javaClass.getField("name").get(javaObject))
        }
        throw InvalidVariableName(name)
    }

    fun toCopper(): CopperValue {
        return when (javaObject) {
            is Number -> CopperNumber(javaObject)
            is String -> CopperString(javaObject)
            is Boolean -> CopperBool(javaObject)
            else -> this
        }
    }
}

