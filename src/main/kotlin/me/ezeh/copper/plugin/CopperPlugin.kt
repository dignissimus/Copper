package me.ezeh.copper.plugin

import me.ezeh.copper.config.JsonConfiguration
import me.ezeh.copper.lang.CopperProgramme
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.PluginBase
import org.bukkit.plugin.PluginDescriptionFile
import java.io.File
import java.io.InputStream

class CopperPlugin(val programme: CopperProgramme, private val pluginLoader: CopperPluginLoader) : PluginBase() {
    companion object {
        val CONFIG_FILE_NAME = "config.json"
    }

    private val pluginDataFolder = File(pluginLoader.scriptDirectory, description.name)
    private val configFile = File(pluginDataFolder, CONFIG_FILE_NAME)
    private val pluginConfig = JsonConfiguration()
    private var pluginNaggable = false
    private var pluginIsEnabled = false

    override fun getDataFolder() = pluginDataFolder

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        TODO("not implemented")
    }

    override fun saveDefaultConfig() {
        TODO("not implemented")
    }

    override fun getResource(resource: String): InputStream {
        TODO("not implemented")
    }

    override fun onTabComplete(sender: CommandSender, command: Command, p2: String, args: Array<String>): MutableList<String> {
        TODO("not implemented")
    }

    override fun isNaggable() = pluginNaggable

    override fun getLogger() = Bukkit.getLogger()

    override fun reloadConfig() = pluginConfig.load(configFile)

    override fun onEnable() {
        programme.execute()
        pluginIsEnabled = true
    }

    override fun isEnabled() = pluginIsEnabled

    override fun onLoad() {
        // Do nothing
    }

    override fun setNaggable(naggable: Boolean) {
        pluginNaggable = naggable
    }

    override fun getConfig() = pluginConfig

    override fun getPluginLoader() = pluginLoader


    override fun getDescription(): PluginDescriptionFile = programme.info.asDescriptionFile()

    override fun getServer() = Bukkit.getServer()

    override fun onDisable() {
        if (programme.environment.hasMethod("onDisable")) {
            programme.environment.getMethod("onDisable").call()
        }
        pluginIsEnabled = false
    }

    override fun getDefaultWorldGenerator(worldName: String, id: String): ChunkGenerator? = null

    override fun saveConfig() = config.save(configFile)

    override fun saveResource(p0: String?, p1: Boolean) {
        TODO("not implemented")
    }

}