package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

open class CopperException(val langKey: CopperLangKey) : Exception() // TODO: line number and preview