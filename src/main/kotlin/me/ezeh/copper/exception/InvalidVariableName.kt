package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class InvalidVariableName(variableName: String) : CopperException(CopperLangKey.INVALID_VARIABLE_NAME) {
    init {
        set("name", variableName)
    }
}