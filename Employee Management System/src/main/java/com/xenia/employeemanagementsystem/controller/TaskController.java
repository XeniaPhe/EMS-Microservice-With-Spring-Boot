package com.xenia.employeemanagementsystem.controller;

import com.xenia.employeemanagementsystem.dto.request.create.TaskCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.TaskUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.TaskResponse;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.TaskCriteria;
import com.xenia.employeemanagementsystem.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskCreateRequest task){
        return new ResponseEntity(taskService.add(task), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") int id){
        return new ResponseEntity(taskService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskUpdateRequest task){
        return new ResponseEntity(taskService.update(task), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id){
        taskService.deleteById(id);
        return new ResponseEntity(String.format("Task with id:%d deleted", id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> findTasks(
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

        TaskCriteria criteria = new TaskCriteria(null, name, priority, priorityUpper, OperationTypeURI.tryConvert(priorityOp),
                issueDate, issueDateAfter, OperationTypeURI.tryConvert(issueDateOp), dueDate, dueDateAfter,
                OperationTypeURI.tryConvert(dueDateOp), terminationDate, terminationDateAfter,
                OperationTypeURI.tryConvert(terminationDateOp), departmentId);

        List<TaskResponse> responseList = criteria.isAllCriteriaEmpty() ? taskService.findAll() :
                taskService.findByCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<TaskResponse>> findTasksByDepartment(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer managerId) {

        DepartmentCriteria criteria = new DepartmentCriteria(id, name, managerId);

        List<TaskResponse> responseList = criteria.isAllCriteriaEmpty() ? taskService.findAll() :
                taskService.findByDepartmentCriteria(criteria);

        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
