package me.ezeh.copper.lang

import org.bukkit.plugin.PluginDescriptionFile

class CopperInfo(override val value: Map<String, CopperValue>) : CopperValue() {
    fun asDescriptionFile() = PluginDescriptionFile(value["name"]?.asValue()?.value.toString(), value["version"]?.asValue()?.value.toString(), "")
}