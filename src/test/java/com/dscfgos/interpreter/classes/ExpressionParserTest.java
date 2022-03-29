package com.dscfgos.interpreter.classes;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpressionParserTest {

    ExpressionParser interpreterParser;

    @BeforeEach
    void setUp() {
        interpreterParser = new ExpressionParser();
    }

    @Test
    @DisplayName("No parameter operation")
    void interpreteNoParams() {
        String result = interpreterParser.interprete("Simple String");
        assertTrue(result.equalsIgnoreCase("Simple String"));
    }

    @Test
    @DisplayName("Single String Parameter")
    void interpreteSingleStringParam() {
        Property property1 = new Property("property_1_name","with Params");
        List<Property> properties = new ArrayList<Property>();
        properties.add(property1);

        String result = interpreterParser.interprete("Simple String {property_1_name}", properties);

        assertTrue(result.equalsIgnoreCase("Simple String with Params"));
    }

    @Test
    @DisplayName("Single String Type String Parameter")
    void interpreteSingleStringTypedParam() {
        Property property1 = new Property("property_1_name","with Typed Params");
        List<Property> properties = new ArrayList<Property>();
        properties.add(property1);

        String result = interpreterParser.interprete("Simple String $S{property_1_name}", properties);

        assertTrue(result.equalsIgnoreCase("Simple String with Typed Params"));
    }

    @Test
    @DisplayName("Convert Expression to Postfix")
    void convertToPostFixExpression() {
        String infixExp1 = "a+b*(c^d-e)^(f+g*h)-i";
        String infixExp2 = "K + L - M*N + (O^P) * W/U/V * T + Q";

        String result1 = interpreterParser.convertToPostFixExpression(infixExp1);
        String result2 = interpreterParser.convertToPostFixExpression(infixExp2);

        assertAll(() -> assertTrue(result1.equalsIgnoreCase(" a b c d ^ e - f g h * + ^ * + i -")),
                () -> assertTrue(result2.equalsIgnoreCase(" K L + M N * - O P ^ W * U / V / T * + Q +")));
    }

    @Test
    @DisplayName("Evaluate Arithmetic Infix Expression")
    void evaluateArithmeticExpression() {
        String expression = "2 * (100 - 200) + 300 * 2";

        String result1 = interpreterParser.evaluateInfixArithmeticExpression(expression);

        assertTrue(result1.equalsIgnoreCase("400.0"));
    }

    @Test
    @DisplayName("Evaluate Conditional Infix Expression")
    void evaluateConditionalExpression() {
        String expression = "true & false | true";
        String expression2 = "true && false || true";

        String result1 = interpreterParser.evaluateInfixConditionalExpression(expression2);


        assertTrue(result1.equalsIgnoreCase("true"));
    }

}