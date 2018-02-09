package me.ezeh.copper.event

import me.ezeh.copper.lang.CopperJavaObject
import me.ezeh.copper.lang.CopperValue
import org.bukkit.event.Event

class CopperBukkitEvent(event: Event) : CopperEvent(event.eventName) {
    override val eventObject = CopperJavaObject(event)
    override fun checkFilter(name: String, value: CopperValue): Boolean { // Default
        return eventObject.getVariable(name).toCopper() == value
    }
}

