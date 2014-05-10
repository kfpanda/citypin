package com.util.common.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SQLExpressionSupport implements SQLExpression {
	private ExpressionParser parser = new SpelExpressionParser();
	private StandardEvaluationContext context = new StandardEvaluationContext();

	@Override
	public void setVariable(String name, Object value) {
		context.setVariable(name, value);
	}

	@Override
	public String parser(String sql) {
		Expression exp = parser.parseExpression(sql, new TemplateParserContext());
		return (String)exp.getValue(context, String.class);
	}

}
