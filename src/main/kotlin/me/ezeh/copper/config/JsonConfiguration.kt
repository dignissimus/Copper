package me.ezeh.copper.config

import org.bukkit.configuration.file.FileConfiguration
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.bukkit.Color
import org.bukkit.OfflinePlayer
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector

class JsonConfiguration : FileConfiguration() {
    private var values = emptyMap<String, Any>()
    private val mapType = object : TypeToken<HashMap<String, Any>>() {}.type
    override fun loadFromString(contents: String) {
        values = Gson().fromJson(contents, mapType)
    }

    override fun saveToString(): String = Gson().toJson(values)


    override fun buildHeader() = ""

    override fun getKeys(deep: Boolean) = values.keys // TODO: deep

    override fun getValues(deep: Boolean) = values

    override fun contains(path: String) = contains(path, false)

    override fun contains(path: String, ignoreDefault: Boolean) = get(path) != null

    override fun isSet(path: String) = contains(path)
    /* TODO implement
    override fun getCurrentPath() = super.getCurrentPath()

    override fun getName() = super.getName()

    override fun getRoot() = super.getRoot()

    override fun getParent() = super.getParent()
    */

    override fun get(path: String) = get(path, getDefault(path)) // TODO: get(parent.child.value)

    override fun get(path: String, default: Any?) = values[path] ?: default

    override fun set(key: String, value: Any) {
        map[key] = value
    }

    /*
    override fun createSection(path: String) = super.createSection(path)

    override fun createSection(path: String, map: Map<*, *>) = super.createSection(path, map)
    */
    override fun getString(path: String) = get(path) as String

    override fun getString(path: String, default: String) = get(path, default) as String

    override fun isString(path: String) = get(path) is String

    override fun getInt(path: String) = get(path) as Int

    override fun getInt(path: String, default: Int) = get(path, default) as Int

    override fun isInt(path: String) = get(path) is Int

    override fun getBoolean(path: String) = get(path) as Boolean

    override fun getBoolean(path: String, default: Boolean) = get(path, default) as Boolean

    override fun isBoolean(path: String) = get(path) is Boolean

    override fun getDouble(path: String) = get(path) as Double

    override fun getDouble(path: String, default: Double) = get(path, default) as Double

    override fun isDouble(path: String) = get(path) is Double

    override fun getLong(path: String) = get(path) as Long

    override fun getLong(path: String, default: Long) = get(path, default) as Long

    override fun isLong(path: String) = get(path) is Long

    override fun getList(path: String) = get(path) as List<*>

    override fun getList(path: String, default: List<*>) = get(path) as List<*>

    override fun isList(path: String) = get(path) is List<*>

    override fun getStringList(path: String): List<String> = get(path) as List<String>

    override fun getIntegerList(path: String) = get(path) as List<Int>

    override fun getBooleanList(path: String): List<Boolean> = get(path) as List<Boolean>

    override fun getDoubleList(path: String): List<Double> = get(path) as List<Double>

    override fun getFloatList(path: String): List<Float> = get(path) as List<Float>

    override fun getLongList(path: String): List<Long> = get(path) as List<Long>

    override fun getByteList(path: String): List<Byte> = get(path) as List<Byte>

    override fun getCharacterList(path: String) = get(path) as List<Char>

    override fun getShortList(path: String): List<Short> = get(path) as List<Short>

    override fun getMapList(path: String) = get(path) as List<Map<*, *>>

    override fun getVector(path: String) = get(path) as Vector

    override fun getVector(path: String, default: Vector) = get(path, default) as Vector

    override fun isVector(path: String) = get(path) is Vector

    override fun getOfflinePlayer(path: String) = get(path) as OfflinePlayer

    override fun getOfflinePlayer(path: String, default: OfflinePlayer) = get(path, default) as OfflinePlayer

    override fun isOfflinePlayer(path: String) = get(path) is OfflinePlayer

    override fun getItemStack(path: String) = get(path) as ItemStack

    override fun getItemStack(path: String, default: ItemStack) = get(path, default) as ItemStack

    override fun isItemStack(path: String) = get(path) is ItemStack

    override fun getColor(path: String) = get(path) as Color

    override fun getColor(path: String, default: Color) = get(path, default) as Color

    override fun isColor(path: String) = get(path) is Color

    override fun getConfigurationSection(path: String) = get(path) as ConfigurationSection

    override fun isConfigurationSection(path: String) = get(path) is ConfigurationSection

    /*
    override fun getDefaultSection() = super.getDefaultSection()

    override fun addDefault(path: String, default: Any) = super.addDefault()
    */
}