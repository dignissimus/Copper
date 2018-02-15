package me.ezeh.copper.plugin

import me.ezeh.copper.lang.*
import me.ezeh.copper.lang.operation.binary.*
import me.ezeh.copper.lang.operation.unary.CopperFactorial
import me.ezeh.copper.lang.operation.unary.CopperLogicalNot
import me.ezeh.copper.lang.operation.unary.CopperNegation
import me.ezeh.copper.lang.operation.unary.CopperPercent

class CopperPluginParser : CopperBaseVisitor<CopperExpression>() {
    val programme = CopperProgramme()
    override fun visitProgramme(context: CopperParser.ProgrammeContext): CopperProgramme {
        val info: CopperInfo

        if (context.info() != null) {
            info = visitInfo(context.info())
        } else {
            throw TODO("Create Exception")
        }

        programme.info = info

        for (methodDeclarationContext in context.methodDeclaration()) {
            programme.environment.addMethod(visitMethodDeclaration(methodDeclarationContext))
        }
        for (expressionContext in context.expression()) {
            programme.addExpression(visitExpression(expressionContext))
        }
        for(listenerContext in context.listener()){
            programme.addListener(visitListener(listenerContext))
        }
        for (classDeclarationContext in context.classDeclaration()) {
            programme.addClass(visitClassDeclaration(classDeclarationContext))
        }

        return programme
    }

    override fun visitExpression(context: CopperParser.ExpressionContext): CopperExpression { // TODO: move from assertions to exceptions, i18n on exceptions
        if (context.binop != null) {
            // Binary operation
            val left = visitExpression(context.left)
            val right = visitExpression(context.right)
            val binop = context.binop.text
            return when (binop) {
                "*" -> CopperMultiplication(programme, left, right)
                "/" -> CopperDivision(programme, left, right)
                "%" -> CopperModulus(programme, left, right)
                "+" -> CopperAddition(programme, left, right)
                "==" -> CopperEquality(programme, left, right)
                "||", "or" -> CopperLogicalOr(programme, left, right)
                "&&", "and" -> CopperLogicalAnd(programme, left, right)
                else -> throw  AssertionError("$binop does not match any binary operator")
            }
        }
        if (context.postunop != null) {
            val operator = context.postunop.text
            val operand = visitExpression(context.operand)
            return when (operator) {
                "!" -> CopperFactorial(programme, operand)
                "%" -> CopperPercent(programme, operand)
                else -> throw AssertionError("$operator does not match any Postfix Unary Operator")
            }
        }
        if (context.preunop != null) {
            val operator = context.preunop.text
            val operand = visitExpression(context.operand)
            return when (operator) {
                "-" -> CopperNegation(programme, operand)
                "!", "not" -> CopperLogicalNot(programme, operand)
                else -> throw AssertionError("$operator does not match any Prefix Unary Operator")
            }
        }
        return super.visitExpression(context)
    }

    override fun visitClassDeclaration(context: CopperParser.ClassDeclarationContext): CopperClass {
        val className = context.class_name.text
        val methods = context.methodDeclaration().map { visitMethodDeclaration(it) }
        val init = if (context.init() != null) visitInit(context.init()) else null
        return CopperClass(programme, className, init, methods)
    }

    override fun visitInit(context: CopperParser.InitContext): CopperMethodDeclaration { // TODO: method?
        return CopperMethodDeclaration("init", emptyArray(), emptyArray(), programme)
    }

    override fun visitListener(context: CopperParser.ListenerContext): CopperListener {
        val eventName = context.event_name.text
        val filterKeys = context.variable().map { it.text }
        val expressions = context.expression().map { visitExpression(it) }
        val filterValues = expressions.dropLast(expressions.size - filterKeys.size) // return all the filter values
        val filters = filterKeys.zip(filterValues)

        return CopperListener(eventName, filters, programme, expressions)
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

        return CopperMethodDeclaration(context.method_name.text, context.variable().map { CopperParameter(it.text) },
                if (context.EQUALS() != null)
                    context.expression().map { CopperReturn(visitExpression(it)) } // only one expression
                else
                    context.expression().map { visitExpression(it) },
                programme, context.STATIC() != null)
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

