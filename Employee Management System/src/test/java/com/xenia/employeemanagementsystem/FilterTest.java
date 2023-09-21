package com.xenia.employeemanagementsystem;

import com.xenia.employeemanagementsystem.filter.core.BasicFilter;
import com.xenia.employeemanagementsystem.filter.core.BetweenFilter;
import com.xenia.employeemanagementsystem.filter.core.CompositeFilter;
import com.xenia.employeemanagementsystem.filter.core.FilterComponent;
import com.xenia.employeemanagementsystem.filter.core.enums.BoundaryMode;
import com.xenia.employeemanagementsystem.filter.core.enums.LogicalOperator;
import com.xenia.employeemanagementsystem.filter.core.enums.OperationTypeHQL;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterTest {
    @Test
    public void TestFilterSystem(){
        //TODO:reduce the number of parentheses
        EmployeeFilter filter = new EmployeeFilter();
        TaskFilter taskFilter = new TaskFilter();

        taskFilter.setPriorityFilter(BasicFilter.<Byte>builder()
                        .operationType(OperationTypeHQL.GT)
                        .value((byte)5).
                        build());

        try{
            taskFilter.setIssueDateFilter(BasicFilter.<Date>builder()
                    .operationType(OperationTypeHQL.LT)
                    .value(new SimpleDateFormat("dd-MM-yyyy").parse("20-07-2017"))
                    .build());
        }catch (ParseException exception){
            throw new IllegalArgumentException();
        }


        filter.setCurrentTaskFilter(null);

        filter.setSalaryFilter(BetweenFilter.<BigDecimal>builder()
                        .lowerBound(BigDecimal.valueOf(10000))
                        .upperBound(BigDecimal.valueOf(20000))
                        .lowerBoundaryMode(BoundaryMode.Inclusive)
                        .upperBoundaryMode(BoundaryMode.Inclusive)
                        .negated(true)
                        .build());

        try {
            filter.setHireDateFilter(CompositeFilter.<Date>builder()
                    .addComponent(FilterComponent.<Date>builder()
                            .logicalOperator(LogicalOperator.OR)
                            .filter(BasicFilter.<Date>builder()
                                    .operationType(OperationTypeHQL.GTE)
                                    .value(new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2016"))
                                    .build())
                            .build())
                    .addComponent(FilterComponent.<Date>builder()
                            .logicalOperator(LogicalOperator.OR)
                            .filter(BasicFilter.<Date>builder()
                                    .operationType(OperationTypeHQL.LTE)
                                    .value(new SimpleDateFormat("dd-MM-yyyy").parse("27-11-2019"))
                                    .build())
                            .build())
                    .build());
        }catch (ParseException exception){
            throw new IllegalArgumentException();
        }

        String filterHQL = filter.toHQL();

        System.out.println(filterHQL);

        //Resulting query:

        /*

         */
    }

    @Test
    public void TestEmptyFilter(){
        EmployeeFilter filter = new EmployeeFilter();

        System.out.println(filter.toHQL());
    }
}
