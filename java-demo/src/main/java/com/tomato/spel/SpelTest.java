package com.tomato.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/9/26 3:49 下午
 * @Version V1.0
 **/
public class SpelTest {

    public static void main(String[] args) {

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello World'");
        String message = expression.getValue().toString();
        System.out.println(message);


    }
}
