package me.ezeh.copper.plugin

import me.ezeh.copper.lang.CopperProgramme
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.PluginBase
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.PluginLoader
import java.io.File
import java.io.InputStream

class CopperPlugin(val programme: CopperProgramme, private val pluginLoader: /*Copper*/PluginLoader) : PluginBase() {
    override fun getDataFolder(): File {
        TODO("not implemented")
    }

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

    override fun isNaggable(): Boolean {
        TODO("not implemented")
    }

    override fun getLogger() = Bukkit.getLogger()

    override fun reloadConfig() {
        TODO("not implemented")
    }

    override fun onEnable() {
        programme.execute()
    }

    override fun isEnabled() = true // TODO?

    override fun onLoad() {
        // Do nothing
    }

    override fun setNaggable(p0: Boolean) {
        TODO("not implemented")
    }

    override fun getConfig(): FileConfiguration {
        TODO("not implemented")
    }

    override fun getPluginLoader() = pluginLoader


    override fun getDescription(): PluginDescriptionFile {
        return PluginDescriptionFile(programme.info.value["name"]?.asValue()?.value.toString(), programme.info.value["version"]?.asValue()?.value.toString(), "")
    }

    override fun getServer() = Bukkit.getServer()

    override fun onDisable() {
        if (programme.environment.hasMethod("ondisable")){

        }
    }

    override fun getDefaultWorldGenerator(p0: String?, p1: String?): ChunkGenerator {
        TODO("not implemented")
    }

    override fun saveConfig() {
        TODO("not implemented")
    }

    override fun saveResource(p0: String?, p1: Boolean) {
        TODO("not implemented")
    }

}