package com.dscfgos.interpreter.flow;

import com.dscfgos.interpreter.utils.ControlFlowUtils;
import com.dscfgos.interpreter.utils.InterpreteProperty;

import java.util.List;
import java.util.Locale;

public class ControlFlowParser {

    public String interprete(String context) {
        return this.interprete(context, null, new Locale("es"));
    }

    public String interprete(String context, List<InterpreteProperty> params, Locale locale) {
        return this.processControlFlows(context, params, locale);
    }

    private String processControlFlows(String context, List<InterpreteProperty> params, Locale locale) {
        return ControlFlowUtils.processControlFlowLines(context, params, locale);
    }
}
