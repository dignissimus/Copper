package me.ezeh.copper.event

import me.ezeh.copper.lang.CopperObject
import me.ezeh.copper.lang.CopperValue
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

abstract class CopperEvent(val name: String) : Event() {
    private val eventHandlers = HandlerList()
    override fun getHandlers() = eventHandlers
    abstract fun checkFilter(name: String, value: CopperValue): Boolean
    abstract val eventObject: CopperObject
}