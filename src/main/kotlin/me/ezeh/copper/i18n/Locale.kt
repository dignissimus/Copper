package me.ezeh.copper.i18n

enum class Locale(val langName: String, val langTag: String, val dialect: String? = null) {
    ENGLISH_GB("English", "en", "gb"),
    ENGLISH_US("English", "en", "us"),
    SPANISH("Spanish", "es")
}