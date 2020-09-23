package br.com.contabancaria.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import org.apache.commons.lang3.StringUtils;

public class QueryDslUtil {

	public static <T> BooleanExpression nullSafeEq(SimpleExpression<T> expression, T value) {
		return value == null ? null : expression.eq(value);
	}

	public static BooleanExpression nullSafeEq(SimpleExpression<String> expression, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return expression.eq(value);
	}

}
