package me.ezeh.copper.lang

class CopperProgramme() : CopperExpression {
    lateinit var info: CopperInfo
    lateinit var methods: Map<String, CopperMethodDeclaration> // TODO: merge methods and variables into 'environment' variable, don't use maps
    val variables = HashMap<String, CopperVariable>()
    lateinit var expressions: List<CopperExpression>

    constructor(info: CopperInfo, methods: Map<String, CopperMethodDeclaration>, expressions: List<CopperExpression>) : this() {
        this.info = info
        this.methods = methods
        this.expressions = expressions
    }

    override fun evaluate(): CopperExpression {
//        for (expression in expressions) {
//            expression.evaluate()
//        }
        return this
    }

    fun execute(): CopperExpression {
        for (expression in expressions) {
            expression.evaluate()
        }
        return this
    }

    constructor(info: CopperInfo, methods: List<CopperMethodDeclaration>, expressions: List<CopperExpression>) : this(info, methods.map { Pair(it.name, it) }.toMap(), expressions)

}

