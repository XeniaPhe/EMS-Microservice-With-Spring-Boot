package com.xenia.employeemanagementsystem.controller;

import com.xenia.employeemanagementsystem.dto.request.create.DepartmentCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.DepartmentUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.EmployeeCriteria;
import com.xenia.employeemanagementsystem.model.Employee;
import com.xenia.employeemanagementsystem.service.DepartmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody DepartmentCreateRequest department){
        return new ResponseEntity(departmentService.add(department), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable("id") int id){
        return new ResponseEntity(departmentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody DepartmentUpdateRequest department){
        return new ResponseEntity(departmentService.update(department), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        departmentService.deleteById(id);
        return new ResponseEntity(String.format("Department with id:%d deleted", id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentResponse>> findDepartments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer managerId){

        DepartmentCriteria criteria = new DepartmentCriteria(null, name, managerId);
        List<DepartmentResponse> responseList = criteria.isAllCriteriaEmpty() ? departmentService.findAll() :
                departmentService.findByCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @GetMapping("/manager")
    public ResponseEntity<List<DepartmentResponse>> findDepartmentsByManager(
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

        List<DepartmentResponse> responseList = criteria.isAllCriteriaEmpty() ? departmentService.findAll() :
                departmentService.findByManagerCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
