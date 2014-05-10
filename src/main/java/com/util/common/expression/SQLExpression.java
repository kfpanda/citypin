package com.util.common.expression;

public interface SQLExpression {
	public void setVariable(String name, Object value);
	public String parser(String sql);
}
