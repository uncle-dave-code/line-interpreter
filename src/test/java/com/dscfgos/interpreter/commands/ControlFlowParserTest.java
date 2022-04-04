package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.ControlFlowUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControlFlowParserTest {

    private CommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void testIfElseStatement() {
        List<ControlFlowBase> commands = new ArrayList<>();

        String testCommands = "#IFELSE(propiedad1 < propiedad2){#FORMAT($NI,propiedad6,'') y #FORMAT($DT,propiedad7,dd/MM/YYYY hh:mm:ss)}:{ELSE RESULT}  --- #IFELSE(1==1){Uno es Igual a Uno}:{otra cosa}";
        List<String> commandsLines = ControlFlowUtils.getControlFlowLines(testCommands);
        if (commandsLines != null && !commandsLines.isEmpty()) {

            var date = LocalDateTime.of(2022, Month.APRIL, 1, 9, 10, 30);

            Property pro1 = new Property("propiedad1", 1);
            Property pro2 = new Property("propiedad2", 2);
            Property pro3 = new Property("propiedad3", 3);
            Property pro4 = new Property("propiedad4", "Result IF");
            Property pro5 = new Property("propiedad5", "Result Else");
            Property pro6 = new Property("propiedad6", 23);
            Property pro7 = new Property("propiedad7", date);

            var result = ControlFlowUtils.processControlFlowLines(testCommands, List.of(pro1, pro2, pro3, pro4, pro5, pro6, pro7), new Locale("es"));

            for (var item : commandsLines) {
                var command = ControlFlowUtils.getControlFlow(item, List.of(pro1, pro2, pro3, pro4, pro5, pro6, pro7), new Locale("es"));
                commands.add(command);
            }
        }
        System.out.println(commands.get(0).evaluate());
    }

}