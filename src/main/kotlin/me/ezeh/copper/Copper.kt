package me.ezeh.copper

import me.ezeh.copper.plugin.CopperPluginLoader
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Copper : JavaPlugin() {
    companion object {
        var instance: Copper? = null
    }
    private val scriptDirectory = File(dataFolder, "scripts")

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
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

    override fun onDisable() {

    }


}