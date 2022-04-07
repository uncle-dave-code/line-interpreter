package com.dscfgos.interpreter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.dscfgos.interpreter.utils.InterpreteProperty;

class SLITest {

    @Test
    void testCommands() {
        String testCommands = "Semana #STR(propiedad1) del #FORMAT($DT,propiedad2,'dd/MM/YYYY') al #FORMAT($DT,propiedad3,dd/MM/YYYY)";

        var date1 = LocalDateTime.of(2022, Month.APRIL, 1, 9, 10, 30);
        var date2 = LocalDateTime.of(2022, Month.APRIL, 6, 9, 10, 30);

        InterpreteProperty pro1 = new InterpreteProperty("propiedad1", 11);
        InterpreteProperty pro2 = new InterpreteProperty("propiedad2", date1);
        InterpreteProperty pro3 = new InterpreteProperty("propiedad3", date2);

        var items = List.of(pro1, pro2, pro3);

        String replaced = SLI.interprete(testCommands, items, new Locale("es"));
        System.out.println(replaced);

        assertTrue(replaced.equalsIgnoreCase("Semana 11 del 01/04/2022 al 06/04/2022"));

    }

    @Test
    void testControlFlow() {
        String testIFELSE1 = "#IFELSE(propiedad1 < propiedad2){IFELSE #1 TRUE}:{IFELSE #1 FALSE}";
        String testIFELSE2 = "#IFELSE(propiedad1 > propiedad2){IFELSE #2 TRUE}:{IFELSE #2 FALSE}";
        String testIF1 = "#IF(propiedad1 < propiedad2){IF #1 TRUE}";
        String testIF2 = "#IF(propiedad1 > propiedad2){IF #2 TRUE}";
        String testIF3 = "#IF(propiedad1 + propiedad3 > propiedad2){IF #2 TRUE}";

        InterpreteProperty pro1 = new InterpreteProperty("propiedad1", 11);
        InterpreteProperty pro2 = new InterpreteProperty("propiedad2", 12);
        InterpreteProperty pro3 = new InterpreteProperty("propiedad3", 10);

        var items = List.of(pro1, pro2, pro3);

        String result1 = SLI.interprete(testIFELSE1, items, new Locale("es"));
        String result2 = SLI.interprete(testIFELSE2, items, new Locale("es"));
        String result3 = SLI.interprete(testIF1, items, new Locale("es"));
        String result4 = SLI.interprete(testIF2, items, new Locale("es"));
        String result5 = SLI.interprete(testIF3, items, new Locale("es"));

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);


        assertAll(
                () -> assertTrue(result1.equalsIgnoreCase("IFELSE #1 TRUE")),
                () -> assertTrue(result2.equalsIgnoreCase("IFELSE #2 FALSE")),
                () -> assertTrue(result3.equalsIgnoreCase("IF #1 TRUE")),
                () -> assertTrue(result4.equalsIgnoreCase("")),
                () -> assertTrue(result5.equalsIgnoreCase("IF #2 TRUE"))
        );

    }

}
