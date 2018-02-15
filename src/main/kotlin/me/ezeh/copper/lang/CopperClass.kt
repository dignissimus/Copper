package me.ezeh.copper.lang

class CopperClass(val programme: CopperProgramme, val name: String, val init: CopperMethodDeclaration?, val methods: List<CopperMethodDeclaration>) : CopperObject() {
    private val staticEnvironment = CopperEnvironment()

    init {
        methods.filter { it.static }.forEach { staticEnvironment.addMethod(it) }
    }

    override fun setVariable(name: String, value: CopperValue) = staticEnvironment.setVariable(name, value)

    override fun getMethod(name: String): CopperMethod = staticEnvironment.getMethod(name)

    override val value = this::class.java

    override fun getVariable(name: String) = staticEnvironment.getVariable(name)

    val constructor = object : CopperMethod(name) { // TODO: multiple constructors, allow definition of constructors
        override fun call(vararg parameters: CopperValue): CopperValue {
            return object : CopperObject() {
                override val value = this::class.java

                private val environment = CopperEnvironment()

                init {
                    methods.map { environment.addMethod(it) }
                    init?.call()
                }

                override fun setVariable(name: String, value: CopperValue) = environment.setVariable(name, value)

                override fun getMethod(name: String) = environment.getMethod(name)

                override fun getVariable(name: String) = environment.getVariable(name)

            }
        }

    }
}