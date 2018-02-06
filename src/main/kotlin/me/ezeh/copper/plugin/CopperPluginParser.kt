package me.ezeh.copper.plugin

import me.ezeh.copper.lang.*

class CopperPluginParser : CopperBaseVisitor<CopperExpression>() {
    val programme = CopperProgramme()
    override fun visitProgramme(context: CopperParser.ProgrammeContext): CopperProgramme {
        val expressions = ArrayList<CopperExpression>()
        val methods = ArrayList<CopperMethodDeclaration>()
        val listeners = ArrayList<CopperListener>()
        val info: CopperInfo

        if (context.info() != null) {
            info = visitInfo(context.info())
        } else {
            throw TODO("Create Exception")
        }

        programme.info = info

        for (methodDeclarationContext in context.methodDeclaration()) {
            methods.add(visitMethodDeclaration(methodDeclarationContext))
        }
        for (expressionContext in context.expression()) {
            programme.addExpression(visitExpression(expressionContext))
        }
        for(listenerContext in context.listener()){
            programme.addListener(visitListener(listenerContext))
        }
        for (method in methods) {
            programme.environment.addMethod(method.name, method)
        }
        return programme
    }

    override fun visitListener(context: CopperParser.ListenerContext): CopperListener {
        val listenerName = context.listener_name.text
        val filterKeys = context.variable().map { it.text }
        val expressions = context.expression().map { visitExpression(it) } // TODO: decide whether to evaluate now or later, currently evaluating when the event is fired
        val filterValues = expressions.dropLast(expressions.size - filterKeys.size) // return all the filter values
        val filters = filterKeys.zip(filterValues)

        return CopperListener(listenerName, filters)
    }

    override fun visitInfo(context: CopperParser.InfoContext): CopperInfo {
        return CopperInfo(context.variable().map { visitVariable(it).name }.zip(context.literal().map { visitLiteral(it).evaluate().asValue() }).toMap())
    }

    override fun visitVariable(context: CopperParser.VariableContext): CopperVariable {
        return CopperVariable(context.text, programme)
    }

    override fun visitAssignment(context: CopperParser.AssignmentContext): CopperAssignment {
        return CopperAssignment(visitVariable(context.variable()), visitExpression(context.expression()), programme)
    }

    override fun visitSuccessful(context: CopperParser.SuccessfulContext): CopperExpression {
        return CopperStatus.SUCCESS
    }

    override fun visitUnsuccessful(context: CopperParser.UnsuccessfulContext): CopperExpression {
        return CopperStatus.FAILURE
    }

    override fun visitReturnStatement(context: CopperParser.ReturnStatementContext): CopperReturn {
        return CopperReturn(visitExpression(context.expression()))
    }

    override fun visitBool(context: CopperParser.BoolContext): CopperBool {
        if (context.TRUE() != null) {
            return CopperBool(true)
        }
        if (context.FALSE() != null) {
            return CopperBool(false)
        }
        throw IllegalStateException()
    }

    override fun visitMethodDeclaration(context: CopperParser.MethodDeclarationContext): CopperMethodDeclaration {

        return CopperMethodDeclaration(context.method_name.text, context.variable().map { CopperParameter(it.text) }, context.expression().map { visitExpression(it) }, programme)
    }

    override fun visitLiteral(context: CopperParser.LiteralContext): CopperExpression {
        if (context.StringLiteral() != null) {
            return CopperString(context.text.substring(1, context.text.length - 1))
        }
        if (context.DecimalLiteral() != null) {
            return CopperNumber(Integer.parseInt(context.text))
        }
        if (context.HexLiteral() != null) {
            return CopperNumber(Integer.parseInt(context.text.substring(2), 16))
        }
        throw IllegalStateException()
    }

    override fun visitMethodCall(context: CopperParser.MethodCallContext): CopperMethodCall {
        return CopperMethodCall(context.method_name.text, context.expression().map { visitExpression(it) }, programme)
    }

    override fun visitIfStatement(context: CopperParser.IfStatementContext): CopperIfStatement {
        val elseStatement: CopperElseStatement
        if (context.elseStatement() != null) {

            return CopperIfStatement(visitExpression(context.expression()[0]), context.expression().drop(1).map { visitExpression(it) }, visitElseStatement(context.elseStatement()))
        }
        return CopperIfStatement(visitExpression(context.expression()[0]), context.expression().drop(1).map { visitExpression(it) })
    }

    override fun visitElseStatement(context: CopperParser.ElseStatementContext): CopperElseStatement {
        return CopperElseStatement(context.expression().map { visitExpression(it) })
    }
}

