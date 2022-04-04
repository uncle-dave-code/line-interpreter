package com.dscfgos.interpreter.flow;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.ControlFlowUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ControlFlowParser {

    public String interprete(String context) {
        return this.interprete(context, null, new Locale("es"));
    }

    public String interprete(String context, List<Property> params, Locale locale) {
        return this.processControlFlows(context,params,locale);
    }

    private String processControlFlows(String context, List<Property> params, Locale locale){
        return ControlFlowUtils.processControlFlowLines(context, params, locale);
    }
}
