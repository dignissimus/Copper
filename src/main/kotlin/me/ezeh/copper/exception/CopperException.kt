package me.ezeh.copper.exception

import me.ezeh.copper.i18n.CopperLangKey
import me.ezeh.copper.i18n.LangStorage
import org.jtwig.JtwigModel
import org.jtwig.JtwigTemplate

abstract class CopperException(val langKey: CopperLangKey) : Exception() {
    override val message: String
        get() = template.render(model)
    val template = JtwigTemplate.inlineTemplate(LangStorage.getFormatString(langKey))
    val model = JtwigModel.newModel()
    fun set(key: String, value: Any) {
        model.with(key, value)
    }
}