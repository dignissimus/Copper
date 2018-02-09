package me.ezeh.copper.lang

import me.ezeh.copper.event.CopperEvent
import me.ezeh.copper.plugin.CopperPlugin
import org.bukkit.Bukkit

class CopperProgramme : CopperExpression {
    companion object {
        private val prgrammes = arrayListOf<CopperProgramme>()
        fun fireEvent(event: CopperEvent) {
            prgrammes.map { it.fireEvent(event) }
        }
    }
    // TODO: does this have to be lateinit?
    lateinit var info: CopperInfo
    val listeners = mutableListOf<CopperListener>()
    val expressions = mutableListOf<CopperExpression>()
    val environment = CopperEnvironment()
    val plugin: CopperPlugin
        get() = Bukkit.getPluginManager().getPlugin(info.asDescriptionFile().name) as CopperPlugin

    init {
        for (listener in listeners) {
            Bukkit.getServer().pluginManager.registerEvents(listener, plugin)
        }
        prgrammes.add(this)
    }

    fun execute(): CopperExpression {
        for (expression in expressions) {
            expression.evaluate()
        }
        return this
    }

    fun addExpression(expression: CopperExpression) {
        expressions.add(expression)
    }

    fun addListener(listener: CopperListener) {
        listeners.add(listener)
    }

    fun fireEvent(event: CopperEvent) {
        listeners.filter { it.eventName == event.name }.map { it.executeCopperEvent(event) }
    }

}

