package me.ezeh.copper

import me.ezeh.copper.event.CopperBukkitPluginListener
import me.ezeh.copper.plugin.CopperPluginLoader
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Copper : JavaPlugin() {
    private val scriptDirectory = File(dataFolder, "scripts")
    private val copperEventListener = CopperBukkitPluginListener(this) // TODO: Move elsewhere?

    override fun onEnable() {
        server.pluginManager.registerEvents(copperEventListener, this)
        if (!scriptDirectory.exists())
            if (!scriptDirectory.mkdirs()) {
                logger.severe("Could not create 'scripts' directory, disabling")
                server.pluginManager.disablePlugin(this)
            }

        val scripts = scriptDirectory.listFiles().filter { it.name.endsWith(".cp") }
        val pluginLoader = CopperPluginLoader(scriptDirectory)
        for (script in scripts) {
            val plugin = pluginLoader.loadPlugin(script)
            pluginLoader.enablePlugin(plugin)
        }
    }
}