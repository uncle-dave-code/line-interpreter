package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void testFormatCommand() {
        List<CommandBase> commands = new ArrayList<>();

        String testCommands = "Semana: #FORMAT($NI,propiedad5,'') del #FORMAT($DT,propiedad1,dd/MM/YYYY hh:mm:ss) al #FORMAT($D,propiedad2,'dd/MM/YYYY') total: #FORMAT($NF,propiedad4,'#,###.##')";
        //String testCommands = "EJEMPLO DateTime: #FORMAT($DT,propiedad1,dd/MM/YYYY hh:mm:ss) EJEMPLO Date: #FORMAT($D,propiedad2,dd/MM/YYYY) EJEMPLO Time: #FORMAT($T,propiedad3,hh:mm:ss)";
        List<String> commandsLines = CommandsUtils.getCommandsLines(testCommands);
        if (commandsLines != null && !commandsLines.isEmpty()) {

            var date = LocalDateTime.of(2022, Month.APRIL, 1, 9, 10, 30);

            Property pro1 = new Property("propiedad1", date);
            Property pro2 = new Property("propiedad2", date.toLocalDate());
            Property pro3 = new Property("propiedad3", date.toLocalTime());
            Property pro4 = new Property("propiedad4", 23.34);
            Property pro5 = new Property("propiedad5", 11);


            for (var item : commandsLines) {
                var command = CommandsUtils.getCommand(item, List.of(pro1, pro2, pro3, pro4, pro5), new Locale("es"));
                commands.add(command);
            }
        }
        System.out.println(commands.get(0).execute());
        System.out.println(commands.get(3).execute());




        assertAll(
                () -> assertTrue(commands.get(0).execute().equalsIgnoreCase("11")),
                () -> assertTrue(commands.get(1).execute().equalsIgnoreCase("01/04/2022 09:10:30")),
                () -> assertTrue(commands.get(2).execute().equalsIgnoreCase("01/04/2022")),
                () -> assertTrue(commands.get(3).execute().equalsIgnoreCase("23,34"))
        );
    }


    @Test
    void testToStringCommand() {
        List<CommandBase> commands = new ArrayList<>();

        String testCommands = "      #STR(propiedad1) #STR(propiedad2) #STR(propiedad3) #STR(propiedad4) #STR(propiedad5) #STR(propiedad6)";
        List<String> commandsLines = CommandsUtils.getCommandsLines(testCommands);

        if (commandsLines != null && !commandsLines.isEmpty()) {
            var date = LocalDateTime.of(2022, Month.APRIL, 1, 9, 10, 30);

            Property pro1 = new Property("propiedad1", 11);
            Property pro2 = new Property("propiedad2", 23.34);
            Property pro3 = new Property("propiedad3", "Un Texto");
            Property pro4 = new Property("propiedad4", date);
            Property pro5 = new Property("propiedad5", date.toLocalDate());
            Property pro6 = new Property("propiedad6", date.toLocalTime());
            var items = List.of(pro1, pro2, pro3, pro4, pro5, pro6);

            for (var item : commandsLines) {
                var command = CommandsUtils.getCommand(item,items, new Locale("es"));
                commands.add(command);
                System.out.println(command.execute());
            }

            assertAll(
                    () -> assertTrue(commands.get(0).execute().equalsIgnoreCase("11")),
                    () -> assertTrue(commands.get(1).execute().equalsIgnoreCase("23.34")),
                    () -> assertTrue(commands.get(2).execute().equalsIgnoreCase("Un Texto"))
                    );
        }

    }
}