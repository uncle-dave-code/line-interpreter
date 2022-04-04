package com.dscfgos.interpreter.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CommandsUtilsTest {
    private CommandsUtils utils ;
    @BeforeEach
    void setUp() {
        this.utils  = new CommandsUtils();
    }

    @Test
    @DisplayName("spliting simple directive")
    void splitDirective() {

        String pattern = "(#FORMAT)|(#IF)";
        String text = "#FORMAT(tipo,propiedad,'formato') #IF(sdfdsf)#### #IF #FORMAT";

        var list = CommandsUtils.getCommandsLines(text);

        for (var command:list) {
            System.out.println(command);
        }
    }

    @Test
    @DisplayName("spliting simple directive")
    void splitFlowControl() {

        String text = "#IF(asdasdsad){asdasdsadasd} #IFELSE(asdasdsad){asdasd}:{asdasdasd} asdasdasd #IF(sdasd){sdfsdfdsfsd}";

        var list = ControlFlowUtils.getControlFlowLines(text);

        for (var command:list) {
            System.out.println(command);
        }
    }


}