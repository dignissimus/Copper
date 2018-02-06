package me.ezeh.copper

import me.ezeh.copper.plugin.CopperPluginLoader
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals

class ListenerTest {
    @Rule
    @JvmField
    val tempFolder = TemporaryFolder()


    @Test
    fun testListeners() {
        val scriptDir = tempFolder.newFolder("scriptDir")

        val testFile = File(this.javaClass.classLoader.getResource("test.cp").file)
        val loader = CopperPluginLoader(scriptDir)
        val plugin = loader.loadPlugin(testFile)

        val listeners = plugin.programme.listeners
        assertEquals(1, listeners.size)

        val listener = listeners[0]
        assertEquals("PlayerJoinEvent", listener.listenerName)

        val filters = listener.filters
        assertEquals(1, filters.size)

        val playerFilter = filters[0]
        val filterName = playerFilter.first
        val filterValue = playerFilter.second
        assertEquals("player", filterName)
        assertEquals("Notch", filterValue.asValue().value)
    }
}