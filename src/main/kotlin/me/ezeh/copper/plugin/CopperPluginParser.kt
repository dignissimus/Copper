package me.ezeh.copper.plugin

import me.ezeh.copper.lang.CopperBaseVisitor
import me.ezeh.copper.lang.CopperMethodDeclaration
import me.ezeh.copper.lang.CopperParser
import me.ezeh.copper.lang.CopperProgramme
import me.ezeh.copper.lang.*

class CopperPluginParser : CopperBaseVisitor<CopperExpression>() {
    lateinit var programme: CopperProgramme
    override fun visitProgramme(context: CopperParser.ProgrammeContext): CopperProgramme {
        val programme = CopperProgramme()
        val expressions = ArrayList<CopperExpression>()
        val methods = ArrayList<CopperMethodDeclaration>()
        val info: CopperInfo
        if (context.info() != null) {
            info = visitInfo(context.info())
        } else {
            throw TODO("Create Exception")
        }

        for (methodDeclarationContext in context.methodDeclaration()) {
            methods.add(visitMethodDeclaration(methodDeclarationContext))
        }
        for (expressionContext in context.expression()) {
            expressions.add(visitExpression(expressionContext))
        }
        programme.info = info
        programme.methods = methods.map { it.name }.zip(methods).toMap()
        programme.expressions = expressions
        return programme
    }

    override fun visitInfo(context: CopperParser.InfoContext): CopperInfo {
        return CopperInfo(context.variable().map { visitVariable(it).name }.zip(context.literal().map { visitLiteral(it) }).toMap())
    }

    override fun visitVariable(context: CopperParser.VariableContext): CopperVariable {
        return CopperVariable(context.text)
    }

    override fun visitBool(context: CopperParser.BoolContext): CopperExpression {
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

