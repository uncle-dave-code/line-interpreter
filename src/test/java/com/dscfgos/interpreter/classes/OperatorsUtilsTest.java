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

        String textMixed = "2 * (100 - 200) + 300 * 2 / 4^2 && 22 || 44";
        String textMixed2 = "2";

        //     +-*/()^&|
        //String textMixed = "@300@HelloWorld@This:Is@A#Java#Program";
        String[] splitsMixed = textMixed.split("((?=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|)|(?<=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|))");
        String[] splitsMixed2 = textMixed2.split("((?=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|)|(?<=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|))");


        assertEquals(this.utils.splitDirective(directive).size(),3);
    }
}