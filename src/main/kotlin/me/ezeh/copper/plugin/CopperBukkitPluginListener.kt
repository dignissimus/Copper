package me.ezeh.copper.plugin

import me.ezeh.copper.Copper
import me.ezeh.copper.event.CopperBukkitEvent
import me.ezeh.copper.lang.CopperProgramme
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.reflections.Reflections
import java.lang.reflect.InvocationTargetException

class CopperBukkitPluginListener(val plugin: Copper? = null) : Listener {
    companion object {
        val reflections = Reflections("org.bukkit")
    }

    init {
        if (plugin != null) {
            val handlers = reflections.getSubTypesOf(Event::class.java).mapNotNull {
                val getEventListeners = plugin.server.pluginManager::class.java.getDeclaredMethod("getEventListeners", Event::class.java::class.java)
                getEventListeners.isAccessible = true
                try {
                    getEventListeners.invoke(plugin.server.pluginManager, it) as HandlerList
                }
                catch (exception: InvocationTargetException) {
                    null
                }
                catch (exception: IllegalStateException) {
                    null
                }

            }
            val registeredListeners = plugin.pluginLoader.createRegisteredListeners(this, plugin).map { it.value }
            registeredListeners.map {
                it.map {
                    val listener = it
                    handlers.map {
                        try {
                            it.register(listener)
                        }
                        catch (_: IllegalStateException) {
                            // Ignored
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    fun onEvent(event: Event) {
        CopperProgramme.prgrammes.map { it.fireEvent(CopperBukkitEvent(event)) } // TODO, use Bukkit api
    }
}