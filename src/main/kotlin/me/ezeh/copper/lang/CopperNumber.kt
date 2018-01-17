package me.ezeh.copper.lang

class CopperNumber(override val value: Number) : CopperValue() {
    override fun evaluate(): CopperNumber {
        return this
    }
}