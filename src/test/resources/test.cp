info {
    name: "listener-test"
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
    return if(BOOL) "Boolean Test: Unsuccessful" else "Boolean Test: Successful" // Should enter the else statement // TODO: make `return successful` work
}

print(testIfStatements())