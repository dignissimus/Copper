package me.ezeh.copper.plugin

import me.ezeh.copper.Copper
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

class CopperPluginLoader(val scriptDirectory: File) : PluginLoader {
    override fun createRegisteredListeners(listener: Listener, plugin: Plugin): MutableMap<Class<out Event>, MutableSet<RegisteredListener>> {
        TODO("not implemented")
    }

    override fun disablePlugin(plugin: Plugin) {
        if (plugin !is CopperPlugin)
            throw IllegalArgumentException("Plugin is not associated with this PluginLoader")
        plugin.onDisable()
    }

    override fun getPluginFileFilters(): Array<Pattern> = arrayOf(".*?\\.cp$".toPattern())

    override fun getPluginDescription(file: File): PluginDescriptionFile = this.loadPlugin(file).description


    override fun enablePlugin(plugin: Plugin) {
        if (plugin !is CopperPlugin)
            throw IllegalArgumentException("Plugin is not associated with this PluginLoader")

//        if(plugin.isEnabled) // TODO: isEnabled check

        plugin.onEnable()
        println("Enabled plugin '${plugin.name}'")
    }

    override fun loadPlugin(file: File): CopperPlugin { // TODO: throw InvalidPluginException when necessary
        val code = file.readText()
        val lexed = CopperLexer(CharStreams.fromString(code))
        val tokens = CommonTokenStream(lexed)
        val programme = CopperPluginParser().visitProgramme(CopperParser(tokens).programme())
        val plugin = CopperPlugin(programme, this)

        try { // try except?
            val pluginManager = Bukkit.getPluginManager() as SimplePluginManager
            val spmClass = SimplePluginManager::class.java
            val pluginsField = spmClass.getDeclaredField("plugins")
            pluginsField.isAccessible = true
            val loadedPlugins = pluginsField.get(pluginManager) as ArrayList<Plugin>
            loadedPlugins.add(plugin)
            pluginsField.set(pluginManager, loadedPlugins)
        }
        catch (exception: NullPointerException) {
            // Bukkit class not defined
            println("Bukkit not found")
        }
        plugin.onLoad()
        println("Loaded plugin '${plugin.name}'")
        return plugin
    }
}

