package com.xenia.employeemanagementsystem.service;

import com.xenia.employeemanagementsystem.dto.request.create.DepartmentCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.DepartmentUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.EmployeeCriteria;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.model.Department;
import com.xenia.employeemanagementsystem.repository.DepartmentRepository;
import com.xenia.employeemanagementsystem.repository.EntityRepository;
import com.xenia.employeemanagementsystem.utility.RepositoryUtility;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService extends EntityService<Department, DepartmentCreateRequest, DepartmentUpdateRequest,
        DepartmentResponse, DepartmentFilter, EntityRepository<Department, DepartmentFilter, DepartmentResponse>> {

    //region SETUP
    private DepartmentRepository departmentRepository;
    private ApplicationContext applicationContext;

    public DepartmentService(DepartmentRepository departmentRepository, ApplicationContext applicationContext) {
        this.departmentRepository = departmentRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }

    @Override
    protected EntityRepository<Department, DepartmentFilter, DepartmentResponse> getRepository() {
        return departmentRepository;
    }

    private EmployeeService getEmployeeService(){
        return applicationContext.getBean(EmployeeService.class);
    }

    @Override
    protected DepartmentResponse convertToResponse(Department entity) {
        RepositoryUtility.initializeField(entity.getManager());

        Integer managerId = entity.getManager() == null ? null : entity.getManager().getId();

        return DepartmentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .managerId(managerId)
                .build();
    }

    @Override
    protected Department convertToEntity(DepartmentCreateRequest createRequest) {
        Department department = new Department();

        department.setName(createRequest.getName());
        department.setDescription(createRequest.getDescription());

        if(createRequest.getManagerId() != null)
            department.setManager(getById(getEmployeeService(), createRequest.getManagerId()));

        return department;
    }

    @Override
    protected Department convertToEntity(DepartmentUpdateRequest updateRequest) {
        Department department = getById(updateRequest.getId());

        if(updateRequest.getName() != null)
            department.setName(updateRequest.getName().orElse(null));
        if(updateRequest.getDescription() != null)
            department.setDescription(updateRequest.getDescription().orElse(null));


        if(updateRequest.getManagerId() != null){
            Integer supervisorId = updateRequest.getManagerId().orElse(null);
            department.setManager(supervisorId == null ? null : getById(getEmployeeService(), supervisorId));
        }

        return department;
    }

    //endregion

    public List<DepartmentResponse> findByCriteria(DepartmentCriteria criteria){
        DepartmentFilter filter = criteria.buildFilter();
        return departmentRepository.query(filter);
    }

    public List<DepartmentResponse> findByManagerCriteria(EmployeeCriteria criteria){
        DepartmentFilter filter = new DepartmentFilter();
        EmployeeFilter managerFilter = criteria.buildFilter();
        filter.setManagerFilter(managerFilter);
        return departmentRepository.query(filter);
    }
}
