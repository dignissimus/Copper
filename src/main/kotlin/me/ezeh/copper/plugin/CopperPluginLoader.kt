package me.ezeh.copper.plugin

import me.ezeh.copper.lang.CopperLexer
import me.ezeh.copper.lang.CopperParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.Listener
import org.bukkit.plugin.*
import java.io.File
import java.util.regex.Pattern

class CopperPluginLoader : PluginLoader {
    override fun createRegisteredListeners(p0: Listener, p1: Plugin): MutableMap<Class<out Event>, MutableSet<RegisteredListener>> {
        TODO("not implemented")
    }

    override fun disablePlugin(p0: Plugin) {
        TODO("not implemented")
    }

    override fun getPluginFileFilters(): Array<Pattern> {
        TODO("not implemented")
    }

    override fun getPluginDescription(file: File?): PluginDescriptionFile {
        TODO("not implemented")
    }

    override fun enablePlugin(plugin: Plugin) {
        TODO("not implemented")
    }

    override fun loadPlugin(file: File): CopperPlugin {
        val code = file.readText()
        val lexed = CopperLexer(CharStreams.fromString(code))
        val tokens = CommonTokenStream(lexed)
        val programme = CopperPluginParser().visitProgramme(CopperParser(tokens).programme())
        val plugin = CopperPlugin(programme, this)

        val pluginManager = Bukkit.getPluginManager() as SimplePluginManager
        val spmClass = SimplePluginManager::class.java
        val pluginsField = spmClass.getDeclaredField("plugins")
        pluginsField.isAccessible = true
        val loadedPlugins = pluginsField.get(pluginManager) as ArrayList<Plugin>
        loadedPlugins.add(plugin)
        pluginsField.set(pluginManager, loadedPlugins)

        return plugin
    }
}

