package me.ezeh.copper.i18n

import org.jtwig.JtwigModel
import org.jtwig.JtwigTemplate

enum class CopperLangKey(val defaultString: String) {
    INVALID_METHOD_NAME("The method {{name}} does not exist"),
    INVALID_VARIABLE_NAME("The variable {{name}} could not be resolved as a variable"),
    INVALID_ARGUMENT_LENGTH("The method '{{(name)}}' was expecting to receive {{(expected)}} arguments but received {{(actual)}}!"),
    METHOD_ALREADY_EXISTS("The method '{{(name)}}', already exists");

    fun translate(values: MutableMap<String, Any>): String {
        val template = JtwigTemplate.inlineTemplate(LangStorage.getFormatString(this))
        val model = JtwigModel.newModel(values)
        return template.render(model)
    }
}