package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class NotEnoughArgumentsException(val expected: Int, val actual: Int) : CopperException(CopperLangKey.NOT_ENOUGH_ARGUMENTS) {
    init {
        set("expected", expected)
        set("actual", actual)
    }
}