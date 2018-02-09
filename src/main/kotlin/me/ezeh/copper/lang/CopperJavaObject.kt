package me.ezeh.copper.lang

import me.ezeh.copper.exception.InvalidVariableName

open class CopperJavaObject(private val javaObject: Any) : CopperObject() {
    override val value = javaObject
    private val javaClass = javaObject::class.java

    override fun setVariable(name: String, value: CopperValue) {
        if (javaClass.methods.map { it.name }.contains("set${name.capitalize()}")) {
            // TODO: checks, exceptions
            javaClass.methods.filter { it.name == "set${name.capitalize()}" }[0].invoke(javaObject, value.value)
        }
        if (javaClass.fields.map { it.name }.contains(name)) {
            javaClass.getField(name).set(javaObject, value)
        }
    }

    override fun getMethod(name: String, value: CopperValue): CopperMethod {
        return CopperJavaMethod(javaClass.methods.filter { it.name == name })
    }

    override fun getVariable(name: String): CopperValue {
        if (name.split(".").size > 1) {
            val split = name.split(".")
            var variable: CopperValue? = null
            var index = 0
            while (index < split.size) {
                variable = (variable as? CopperObject)?.getVariable(split[index++]) ?: getVariable(split[index++])
            }
            if (variable != null) {
                return variable
            }
        }
        if (javaClass.methods.map { it.name }.contains("get${name.capitalize()}")) {
            return CopperJavaObject(javaClass.getMethod("get${name.capitalize()}").invoke(javaObject)).toCopper()
        }
        if (javaClass.fields.map { it.name }.contains(name)) {
            return CopperJavaObject(javaClass.getField("name").get(javaObject)).toCopper()
        }
        throw InvalidVariableName(name)
    }

    override fun toCopper(): CopperValue {
        return when (javaObject) {
            is Number -> CopperNumber(javaObject)
            is String -> CopperString(javaObject)
            is Boolean -> CopperBool(javaObject)
            else -> this
        }
    }
}

