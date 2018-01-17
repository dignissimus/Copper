package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class InvalidMethodName(val methodName: String) : CopperException(CopperLangKey.INVALID_METHOD_NAME)