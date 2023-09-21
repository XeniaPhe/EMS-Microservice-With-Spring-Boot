package com.xenia.employeemanagementsystem.filter.core.enums;

import lombok.Getter;

public enum LogicalOperator {
    AND(" AND "),
    OR(" OR ");

    @Getter
    private final String operator;
    @Getter
    private LogicalOperator opposite;

    LogicalOperator(final String operator){
        this.operator = operator;
    }

    static {
        AND.opposite = OR;
        OR.opposite = AND;
    }
}
