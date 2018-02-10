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
    actual = if BOOL then successful else unsuccessful // Unnecessary 'then' // Returns unsuccessful, TODO: fix
}

print("Boolean Test (A)...")
print(testIfStatements())

print("Boolean Test (B)...")
print(testBracelessIfStatement())