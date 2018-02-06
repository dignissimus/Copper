package me.ezeh.copper.lang

abstract class CopperStatus(override val value: Status) : CopperValue() {
    companion object {
        val SUCCESS = CopperSuccess()
        val FAILURE = CopperFailure()
    }

    enum class Status {
        SUCCESSFUL, UNSUCCESSFUL
    }
}