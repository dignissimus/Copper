package me.ezeh.copper.lang

import me.ezeh.copper.event.CopperEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CopperListener(val eventName: String, val filters: List<Pair<String, CopperExpression>>, val programme: CopperProgramme, val expressions: List<CopperExpression>) : CopperExpression, Listener {
    @EventHandler
    fun executeCopperEvent(event: CopperEvent) {
        println("Running: ${event::class.java.simpleName}")
        if (event.name == eventName && filters.map { event.checkFilter(it.first, it.second.evaluate().asValue()) }.all { it }) { // If all filters are true
            programme.environment.setVariable("event", event.eventObject)
            for (expression in expressions)
                expression.evaluate()
            programme.environment.removeVariable("event")
        }
    }
}