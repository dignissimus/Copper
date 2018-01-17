package me.ezeh.copper.lang

class CopperString(override val value: String) : CopperValue() {
    override fun evaluate(): CopperString {
        return this
    }
}