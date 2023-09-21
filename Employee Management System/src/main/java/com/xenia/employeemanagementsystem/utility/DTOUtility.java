package com.xenia.employeemanagementsystem.utility;

import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.dto.response.IEntityResponse;
import com.xenia.employeemanagementsystem.dto.response.TaskResponse;
import com.xenia.employeemanagementsystem.model.Department;
import com.xenia.employeemanagementsystem.model.Employee;
import com.xenia.employeemanagementsystem.model.EntityBase;
import com.xenia.employeemanagementsystem.model.Task;

public class DTOUtility {
    private DTOUtility(){}

    public static String getConstructorExpressionForHQL(Class<? extends IEntityResponse> responseType){
        if (responseType == EmployeeResponse.class)
            return EmployeeResponse.HQL_CONSTRUCTOR_EXPRESSION;
        else if(responseType == DepartmentResponse.class)
            return DepartmentResponse.HQL_CONSTRUCTOR_EXPRESSION;
        else if(responseType == TaskResponse.class)
            return TaskResponse.HQL_CONSTRUCTOR_EXPRESSION;

        throw new InternalError("Unidentified entity");
    }

    public static Class<? extends IEntityResponse> getResponseType(Class<? extends EntityBase> entityType){
        if(entityType == Employee.class)
            return EmployeeResponse.class;
        else if(entityType == Department.class)
            return DepartmentResponse.class;
        else if(entityType == Task.class)
            return TaskResponse.class;

        throw new InternalError("Unidentified entity");
    }
}
