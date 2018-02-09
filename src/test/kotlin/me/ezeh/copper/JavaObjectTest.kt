package me.ezeh.copper

import me.ezeh.copper.lang.CopperEnvironment
import me.ezeh.copper.lang.CopperJavaObject
import org.junit.Test
import kotlin.test.assertEquals

class JavaObjectTest {
    @Test
    fun testJavaObjects() {
        val number = 1
        val numberCopperObject = CopperJavaObject(number)
        assertEquals(number, numberCopperObject.value)

        val pair = Pair("first", "second")
        val pairCopperObject = CopperJavaObject(pair)
        assertEquals(pair.first, pairCopperObject.getVariable("first").value)
        assertEquals(pair.second, pairCopperObject.getVariable("second").value)

        val pairHolder = PairHolder()
        val pairHolderCopperObject = CopperJavaObject(pairHolder)
        assertEquals(pairHolder.pair.first, pairHolderCopperObject.getVariable("pair.first").value)
        assertEquals(pairHolder.pair.second, pairHolderCopperObject.getVariable("pair.second").value)

        val environment = CopperEnvironment()
        environment.setVariable("pairHolder", pairHolderCopperObject )
        assertEquals(pairHolder, environment.getVariable("pairHolder").value)
        assertEquals(pairHolder.pair, environment.getVariable("pairHolder.pair").value)
        assertEquals(pairHolder.pair.first, environment.getVariable("pairHolder.pair.first").value)
        assertEquals(pairHolder.pair.second, environment.getVariable("pairHolder.pair.second").value)
    }

    class PairHolder {
        val pair = Pair("first", "second")
    }

}
