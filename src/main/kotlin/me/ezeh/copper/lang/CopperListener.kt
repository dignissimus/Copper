package me.ezeh.copper.lang

import org.bukkit.event.Listener

class CopperListener(val listenerName: String, val filters: List<Pair<String, CopperExpression>>) : CopperExpression, Listener {

}