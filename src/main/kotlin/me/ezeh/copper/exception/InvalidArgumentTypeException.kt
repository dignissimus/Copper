package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey
import me.ezeh.copper.lang.CopperType

class InvalidArgumentTypeException(val methodName: String, val expected: CopperType, val actual: CopperType) : CopperException(CopperLangKey.INVALID_ARGUMENT_TYPE) {
    init {
        set("name", methodName)
        set("expected", expected)
        set("actual", actual)
    }
}