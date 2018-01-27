package me.ezeh.copper.i18n

enum class CopperLangKey(defaultString: String) { // TODO: use
    INVALID_METHOD_NAME("The method %{name} does not exist"),
    INVALID_VARIABLE_NAME("The variable %{name} could not be resolved as a variable"),
    INVALID_ARGUMENT_LENGTH("The method '%{name}' was expecting to receive %{expected} arguments but received %{actual}!"),
    METHOD_ALREADY_EXISTS("The method '%{name}', already exists")
}