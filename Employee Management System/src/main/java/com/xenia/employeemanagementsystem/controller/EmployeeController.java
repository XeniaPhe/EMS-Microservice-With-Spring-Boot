package com.xenia.employeemanagementsystem.controller;

import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.dto.request.create.EmployeeCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.EmployeeUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.EmployeeCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.TaskCriteria;
import com.xenia.employeemanagementsystem.model.Employee;
import com.xenia.employeemanagementsystem.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeCreateRequest employee){
        return new ResponseEntity(employeeService.add(employee), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") int id){
        return new ResponseEntity(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest employee){
        return new ResponseEntity(employeeService.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteById(id);
        return new ResponseEntity(String.format("Employee with id:%d deleted", id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> findEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Employee.Gender gender,
            @RequestParam(required = false) BigDecimal salary,
            @RequestParam(required = false, defaultValue = "eq") String salaryOp,
            @RequestParam(required = false) BigDecimal salaryUpper,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date hireDate,
            @RequestParam(required = false, defaultValue = "eq") String hireDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date hireDateAfter,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date leaveDate,
            @RequestParam(required = false, defaultValue = "eq") String leaveDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date leaveDateAfter,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Integer supervisorId,
            @RequestParam(required = false) Integer currentTaskId){

        EmployeeCriteria criteria = new EmployeeCriteria(null, firstName, lastName, gender, salary, salaryUpper,
                OperationTypeURI.tryConvert(salaryOp), hireDate, hireDateAfter, OperationTypeURI.tryConvert(hireDateOp),
                leaveDate, leaveDateAfter, OperationTypeURI.tryConvert(leaveDateOp), departmentId, supervisorId, currentTaskId);

        List<EmployeeResponse> responseList = criteria.isAllCriteriaEmpty() ? employeeService.findAll() :
                employeeService.findByCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @GetMapping("/supervisor")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesBySupervisor(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Employee.Gender gender,
            @RequestParam(required = false) BigDecimal salary,
            @RequestParam(required = false, defaultValue = "eq") String salaryOp,
            @RequestParam(required = false) BigDecimal salaryUpper,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date hireDate,
            @RequestParam(required = false, defaultValue = "eq") String hireDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date hireDateAfter,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date leaveDate,
            @RequestParam(required = false, defaultValue = "eq") String leaveDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date leaveDateAfter,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Integer supervisorId,
            @RequestParam(required = false) Integer currentTaskId){

        EmployeeCriteria criteria = new EmployeeCriteria(id, firstName, lastName, gender, salary, salaryUpper,
                OperationTypeURI.tryConvert(salaryOp), hireDate, hireDateAfter, OperationTypeURI.tryConvert(hireDateOp),
                leaveDate, leaveDateAfter, OperationTypeURI.tryConvert(leaveDateOp), departmentId, supervisorId, currentTaskId);

        List<EmployeeResponse> responseList = criteria.isAllCriteriaEmpty() ? employeeService.findAll() :
                employeeService.findBySupervisorCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByDepartment(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer managerId){

        DepartmentCriteria criteria = new DepartmentCriteria(id, name, managerId);

        List<EmployeeResponse> responseList = criteria.isAllCriteriaEmpty() ? employeeService.findAll() :
                employeeService.findByDepartmentCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @GetMapping("/task")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByTask(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Byte priority,
            @RequestParam(required = false) Byte priorityUpper,
            @RequestParam(required = false, defaultValue = "eq") String priorityOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date issueDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date issueDateAfter,
            @RequestParam(required = false, defaultValue = "eq") String issueDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dueDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dueDateAfter,
            @RequestParam(required = false, defaultValue = "eq") String dueDateOp,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date terminationDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date terminationDateAfter,
            @RequestParam(required = false, defaultValue = "eq") String terminationDateOp,
            @RequestParam(required = false) Integer departmentId){

        TaskCriteria criteria = new TaskCriteria(id, name, priority, priorityUpper, OperationTypeURI.tryConvert(priorityOp),
                issueDate, issueDateAfter, OperationTypeURI.tryConvert(issueDateOp), dueDate, dueDateAfter,
                OperationTypeURI.tryConvert(dueDateOp), terminationDate, terminationDateAfter,
                OperationTypeURI.tryConvert(terminationDateOp), departmentId);

        List<EmployeeResponse> responseList = criteria.isAllCriteriaEmpty() ? employeeService.findAll() :
                employeeService.findByTaskCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
