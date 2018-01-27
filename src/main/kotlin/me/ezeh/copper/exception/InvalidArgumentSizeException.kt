package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class InvalidArgumentSizeException(val methodName: String, val expected: Int, val actual: Int) : CopperException(CopperLangKey.INVALID_ARGUMENT_LENGTH)