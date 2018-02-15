info {
    name: "test"
    version: "1.0" // comment test
}

on PlayerJoinEvent(player="Notch"){
    // Code
}

onDisable {
    print("Goodbye!")
}

print("Hello!")

testIfStatements {
    BOOL = false
    return if(BOOL) unsuccessful else successful // Should enter the else statement, can use failed instead of unsuccessful
}

testBracelessIfStatement {
    BOOL = true
    return if BOOL then successful else unsuccessful // Unnecessary 'then'
}

printf("Boolean Test (A) = %s", testIfStatements())
printf("Boolean Test (B) = %s", testBracelessIfStatement())


class ClassName {
    init {
        print("Initialised")
    }

    static staticMethod {
        print("Static Method!")
    }

    function secondMethod() = 5

    function method(){
        return 1
    }
}

ClassName.staticMethod()
object = ClassName()
printf("object.secondMethod() = %d", object.secondMethod())