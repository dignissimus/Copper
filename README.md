# Copper
<p align="center">
  <a href="https://ci.dignissimus.co.uk/job/Copper"> <img src="https://ci.dignissimus.co.uk/job/Copper/badge/icon?branch=master"> </a>
  <a href="LICENSE"> <img src="https://img.shields.io/github/license/spammy23/Copper.svg"> </a>
</p>

## About

> Copper was made to make it easy for Developers to write simple scripts

## Usage
```javascript
info {
  name: "Plugin"
  version: "1.0"
}
```
Every script must start with an info block, this is a bit like the plugin.yml in Java plugins.

Unlike Java Plugins however, there is no need to define an `onEnable` method, you can write your code int the body of the scrpt.
```javascript
print("Hello, World!") // Prints 'Hello, World!' when the plugin is enabled
```

Functions are declared by writing the name of the function folowed by it's parameters. Depending on how you want to write your code, you can add `function` before the name of the function as well as wrapping a pair of brackets around the parameter list.
You can return a value by writing `return` folowed value that you woild like to be returned.
```javascript
function thisWorks parameter, parameter2 {
  // CODE
}

thisAlsoWorks(parameter, parameter2) {
  // CODE
}

function oneMoreExample = 5 // returns 5
```
If statements start with `if` followed by a condition and then some expressions to be evaulated if the condition resolves to be true. An if statement can optionally contain an else statement by it.
```javascript
BOOL = false
if(BOOL){
  // Won't run
}
else {
  // CODE
}
```

If statements are expressions, they return values, this allows them to be used to simplify some otherwise longer statements. For example, this block of code can be simplified.
```javascript
BOOL = true
value = 0
if(BOOL) { // This, can be simplified
  value = 1
}
else {
  value = 2
}

value = if(BOOL) 1 else 2 // Into this
```

With if statements, the brackets around the condition are unneccesary but when leaving them out, it may be better to add a `then` after the condition to make the code easier to comprehend.
```bash
BOOL = false
value = if bool then 0 else 1 // Value should equal 1
```

Classes can be defined with the keyword `class`, they may optionally contain an init block at the top as well as as many other classes or methods inside of it.
```javascript
class MyClass {
  init {
    print("Initialised!")
  }
  
  method {
    print("Hello!")
  }
  
  static staticMethod {
    print("Static!")
  }
}

Myclass.staticMethod()
object = MyClass()
object.method()
```
## Installation

> To install, place the Copper.jar archive in your plugins folder and insert your Copper scripts in the `plugins/Copper/scripts` directory created when the plugin first runs
