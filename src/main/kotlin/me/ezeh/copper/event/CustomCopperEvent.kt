package me.ezeh.copper.event

import me.ezeh.copper.lang.CopperJavaObject
import me.ezeh.copper.lang.CopperObject

abstract class CustomCopperEvent(name: String) : CopperEvent(name) {
    override val eventObject: CopperObject
        get() = CopperJavaObject(this)
}