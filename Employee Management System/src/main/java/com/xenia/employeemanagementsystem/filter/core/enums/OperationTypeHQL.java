package com.xenia.employeemanagementsystem.filter.core.enums;

import lombok.Getter;

public enum OperationTypeHQL {
    EQ(" = "),
    NE(" <> "),
    GT(" > "),
    GTE(" >= "),
    LT(" < "),
    LTE(" <= ");

    @Getter
    private final String operator;
    @Getter
    private OperationTypeHQL opposite;

    OperationTypeHQL(final String operator) {
        this.operator = operator;
    }

    static {
        EQ.opposite = NE;
        NE.opposite = EQ;
        GT.opposite = LTE;
        GTE.opposite = LT;
        LT.opposite = GTE;
        LTE.opposite = GT;
    }


}
