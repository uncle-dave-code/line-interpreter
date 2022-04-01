package com.dscfgos.interpreter;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class SLITest {
    @Test
    void testToStringCommand() {
        List<CommandBase> commands = new ArrayList<>();

        String testCommands = "Semana #STR(propiedad1) del #FORMAT($DT,propiedad2,'dd/MM/YYYY') al #FORMAT($DT,propiedad3,dd/MM/YYYY)";

        var date1 = LocalDateTime.of(2022, Month.APRIL, 1, 9, 10, 30);
        var date2 = LocalDateTime.of(2022, Month.APRIL, 6, 9, 10, 30);

        Property pro1 = new Property("propiedad1", 11);
        Property pro2 = new Property("propiedad2", date1);
        Property pro3 = new Property("propiedad3", date2);
        var items = List.of(pro1, pro2, pro3);

        String replaced = SLI.interprete(testCommands, items, new Locale("es"));

        assertTrue(replaced.equalsIgnoreCase("Semana 11 del 01/04/2022 al 06/04/2022"));
    }
}