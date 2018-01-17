package me.ezeh.copper

import me.ezeh.copper.plugin.CopperPluginLoader
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Copper : JavaPlugin() {
    override fun onEnable() {
        val scriptDir = File(dataFolder, "scripts")

        if (!scriptDir.exists())
            if (!scriptDir.mkdirs()) {
                logger.severe("Could not create 'scripts' directory, disabling")
                server.pluginManager.disablePlugin(this)
            }


        val scripts = scriptDir.listFiles().filter { it.name.endsWith(".cp") }
        for (script in scripts) {
            val plugin = CopperPluginLoader().loadPlugin(script)
            println("Loaded '${plugin.name}'")
        }

    }

    override fun onDisable() {

    }


}