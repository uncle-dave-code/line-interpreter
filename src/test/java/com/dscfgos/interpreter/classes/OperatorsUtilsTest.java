package com.dscfgos.interpreter.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorsUtilsTest {

    private OperatorsUtils utils ;
    @BeforeEach
    void setUp() {
      this.utils  = new OperatorsUtils();
    }

    @Test
    @DisplayName("spliting simple directive")
    void splitDirective() {
        String directive = "12 + 34";

        final String operators = " |\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\||<=|>=|<(?!=)|>(?!=)|==";
        final String operatorsRegEx = "((?=" + operators + ")|(?<=" + operators + "))";

        String textMixed = "2 * (100 - 200) + 300 * 2 / 4^2 && 22 || 44";
        String textMixed2 = " 2 100 200 - * 300 2 * +";
        String textMixed3 = "2<3<=4>6>=9==4";

        //     +-*/()^&|
        //String textMixed = "@300@HelloWorld@This:Is@A#Java#Program";
        String[] splitsMixed = textMixed.split("((?=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|)|(?<=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|))");
        String[] splitsMixed2 = textMixed2.split(operatorsRegEx);
        String[] splitsMixed3 = textMixed3.split(operatorsRegEx);

        var directives = this.utils.splitDirective(textMixed2);

        assertEquals(this.utils.splitDirective(directive).size(),3);
    }
}