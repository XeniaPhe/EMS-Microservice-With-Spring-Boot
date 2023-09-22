package com.xenia.employeemanagementsystem.service;

import com.xenia.employeemanagementsystem.dto.request.create.EmployeeCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.EmployeeUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.EmployeeCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.TaskCriteria;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import com.xenia.employeemanagementsystem.model.Employee;
import com.xenia.employeemanagementsystem.repository.EmployeeRepository;
import com.xenia.employeemanagementsystem.repository.EntityRepository;
import com.xenia.employeemanagementsystem.utility.RepositoryUtility;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class EmployeeService extends EntityService<Employee, EmployeeCreateRequest, EmployeeUpdateRequest,
        EmployeeResponse, EmployeeFilter, EntityRepository<Employee, EmployeeFilter, EmployeeResponse>> {

    //region SETUP
    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;
    private TaskService taskService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.taskService = taskService;
    }

    @Override
    protected Class<Employee> getEntityType() {
        return Employee.class;
    }

    @Override
    protected EntityRepository<Employee, EmployeeFilter, EmployeeResponse> getRepository() {
        return employeeRepository;
    }

    @Override
    protected EmployeeResponse convertToResponse(Employee entity) {
        RepositoryUtility.initializeField(entity.getCurrentTask());
        RepositoryUtility.initializeField(entity.getDepartment());
        RepositoryUtility.initializeField(entity.getSupervisor());

        Integer supervisorId = entity.getSupervisor() == null ? null : entity.getSupervisor().getId();
        Integer currentTaskId = entity.getCurrentTask() == null ? null : entity.getCurrentTask().getId();

        return EmployeeResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .salary(entity.getSalary())
                .hireDate(entity.getHireDate())
                .leaveDate(entity.getLeaveDate())
                .supervisorId(supervisorId)
                .departmentId(entity.getDepartment().getId())
                .currentTaskId(currentTaskId)
                .build();
    }

    @Override
    protected Employee convertToEntity(EmployeeCreateRequest createRequest) {
        Employee employee = new Employee();
        employee.setFirstName(createRequest.getFirstName());
        employee.setLastName(createRequest.getLastName());
        employee.setGender(createRequest.getGender());
        employee.setEmail(createRequest.getEmail());
        employee.setPhoneNumber(createRequest.getPhoneNumber());
        employee.setAddress(createRequest.getAddress());
        employee.setSalary(createRequest.getSalary());
        employee.setDepartment(getById(departmentService, createRequest.getDepartmentId()));

        if(createRequest.getSupervisorId() != null)
            employee.setSupervisor(getById(createRequest.getSupervisorId()));

        return employee;
    }

    @Override
    protected Employee convertToEntity(EmployeeUpdateRequest updateRequest) {
        Employee employee = getById(updateRequest.getId());

        if(updateRequest.getFirstName() != null)
            employee.setFirstName(updateRequest.getFirstName().orElse(null));
        if(updateRequest.getLastName() != null)
            employee.setLastName(updateRequest.getLastName().orElse(null));
        if(updateRequest.getGender() != null)
            employee.setGender(updateRequest.getGender().orElse(null));
        if(updateRequest.getEmail() != null)
            employee.setEmail(updateRequest.getEmail().orElse(null));
        if(updateRequest.getPhoneNumber() != null)
            employee.setPhoneNumber(updateRequest.getPhoneNumber().orElse(null));
        if(updateRequest.getAddress() != null)
            employee.setAddress(updateRequest.getAddress().orElse(null));
        if(updateRequest.getSalary() != null)
            employee.setSalary(updateRequest.getSalary().orElse(null));
        if(updateRequest.getLeaveDate() != null)
            employee.setLeaveDate(updateRequest.getLeaveDate().orElse(null));

        if(updateRequest.getSupervisorId() != null){
            Integer supervisorId = updateRequest.getSupervisorId().orElse(null);
            employee.setSupervisor(supervisorId == null ? null : getById(supervisorId));
        }

        if(updateRequest.getCurrentTaskId() != null){
            Integer currentTaskId = updateRequest.getCurrentTaskId().orElse(null);
            employee.setCurrentTask(currentTaskId == null ? null : getById(taskService, currentTaskId));
        }

        return employee;
    }

    //endregion

    //region CUSTOM QUERIES

    public List<EmployeeResponse> findCurrentlyEmployed(){
        Criteria<Date> criteria = Criteria.<Date>of(null, OperationTypeURI.NE);

        EmployeeFilter filter = new EmployeeFilter();
        filter.setLeaveDateFilter(criteria.buildFilter());

        return employeeRepository.query(filter);
    }

    public List<EmployeeResponse> findFormerEmployees(){
        Criteria<Date> criteria = Criteria.<Date>of(null);

        EmployeeFilter filter = new EmployeeFilter();
        filter.setLeaveDateFilter(criteria.buildFilter());

        return employeeRepository.query(filter);
    }

    public List<EmployeeResponse> findByCriteria(EmployeeCriteria criteria){
        EmployeeFilter filter = criteria.buildFilter();
        return employeeRepository.query(filter);
    }

    public List<EmployeeResponse> findBySupervisorCriteria(EmployeeCriteria criteria){
        EmployeeFilter filter = new EmployeeFilter();
        EmployeeFilter supervisorFilter = criteria.buildFilter();
        filter.setSupervisorFilter(supervisorFilter);
        return employeeRepository.query(filter);
    }

    public List<EmployeeResponse> findByDepartmentCriteria(DepartmentCriteria criteria){
        EmployeeFilter filter = new EmployeeFilter();
        DepartmentFilter departmentFilter = criteria.buildFilter();
        filter.setDepartmentFilter(departmentFilter);
        return employeeRepository.query(filter);
    }

    public List<EmployeeResponse> findByTaskCriteria(TaskCriteria criteria){
        EmployeeFilter filter = new EmployeeFilter();
        TaskFilter taskFilter = criteria.buildFilter();
        filter.setCurrentTaskFilter(taskFilter);
        return employeeRepository.query(filter);
    }

    //endregion
}
