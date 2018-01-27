package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey

class CopperMethodAlreadyExistsException(methodName: String) : CopperException(CopperLangKey.METHOD_ALREADY_EXISTS) {

}