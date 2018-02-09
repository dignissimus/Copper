package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class MethodAlreadyExistsException(methodName: String) : CopperException(CopperLangKey.METHOD_ALREADY_EXISTS) {
    init {
        set("name", methodName)
    }
}