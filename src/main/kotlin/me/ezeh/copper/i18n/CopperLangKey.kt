package me.ezeh.copper.i18n

import org.jtwig.JtwigModel
import org.jtwig.JtwigTemplate

enum class CopperLangKey(val defaultString: String) {
    INVALID_METHOD_NAME("The method {{name}} does not exist"),
    INVALID_VARIABLE_NAME("The variable {{name}} could not be resolved as a variable"),
    INVALID_ARGUMENT_LENGTH("The method '{{name}}' was expecting to receive {{(expected)}} arguments but received {{(actual)}}!"),
    NOT_ENOUGH_ARGUMENTS("The method '{{name}}' needs at least {{expected}} arguments but received only {{actual}}!"),
    METHOD_ALREADY_EXISTS("The method '{{name}}', already exists"),
    INVALID_ARGUMENT_TYPE("The method '{{}}' was expecting a value of type {{type}} but received {{actual}}");
}