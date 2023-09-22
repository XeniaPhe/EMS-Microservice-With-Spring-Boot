package com.xenia.employeemanagementsystem.service;

import com.xenia.employeemanagementsystem.dto.request.create.TaskCreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.TaskUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.TaskResponse;
import com.xenia.employeemanagementsystem.filter.criteria.domain.DepartmentCriteria;
import com.xenia.employeemanagementsystem.filter.criteria.domain.TaskCriteria;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import com.xenia.employeemanagementsystem.model.Task;
import com.xenia.employeemanagementsystem.repository.EntityRepository;
import com.xenia.employeemanagementsystem.repository.TaskRepository;
import com.xenia.employeemanagementsystem.utility.RepositoryUtility;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService extends EntityService<Task, TaskCreateRequest, TaskUpdateRequest,
        TaskResponse, TaskFilter, EntityRepository<Task, TaskFilter, TaskResponse>> {

    //region SETUP

    private TaskRepository taskRepository;
    private DepartmentService departmentService;

    public TaskService(TaskRepository taskRepository, DepartmentService departmentService) {
        this.taskRepository = taskRepository;
        this.departmentService = departmentService;
    }

    @Override
    protected Class<Task> getEntityType() {
        return Task.class;
    }

    @Override
    protected EntityRepository<Task, TaskFilter, TaskResponse> getRepository() {
        return taskRepository;
    }

    @Override
    protected TaskResponse convertToResponse(Task entity) {
        RepositoryUtility.initializeField(entity.getDepartment());

        return TaskResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .priority(entity.getPriority())
                .issueDate(entity.getIssueDate())
                .dueDate(entity.getDueDate())
                .terminationDate(entity.getTerminationDate())
                .departmentId(entity.getDepartment().getId())
                .build();
    }

    @Override
    protected Task convertToEntity(TaskCreateRequest createRequest) {
        Task task = new Task();
        task.setName(createRequest.getName());
        task.setDescription(createRequest.getDescription());
        task.setPriority(createRequest.getPriority());
        task.setDueDate(createRequest.getDueDate());
        task.setDepartment(getById(departmentService, createRequest.getDepartmentId()));

        return task;
    }

    @Override
    protected Task convertToEntity(TaskUpdateRequest updateRequest) {
        Task task = getById(updateRequest.getId());

        if(updateRequest.getName() != null)
            task.setName(updateRequest.getName().orElse(null));
        if(updateRequest.getDescription() != null)
            task.setDescription(updateRequest.getDescription().orElse(null));
        if(updateRequest.getPriority() != null)
            task.setPriority(updateRequest.getPriority().orElse(null));
        if(updateRequest.getDueDate() != null)
            task.setDueDate(updateRequest.getDueDate().orElse(null));
        if(updateRequest.getTerminationDate() != null)
            task.setTerminationDate(updateRequest.getTerminationDate().orElse(null));

        return task;
    }

    //endregion

    public List<TaskResponse> findByCriteria(TaskCriteria criteria){
        TaskFilter filter = criteria.buildFilter();
        return taskRepository.query(filter);
    }

    public List<TaskResponse> findByDepartmentCriteria(DepartmentCriteria criteria){
        TaskFilter filter = new TaskFilter();
        DepartmentFilter departmentFilter = criteria.buildFilter();
        filter.setDepartmentFilter(departmentFilter);
        return taskRepository.query(filter);
    }
}
