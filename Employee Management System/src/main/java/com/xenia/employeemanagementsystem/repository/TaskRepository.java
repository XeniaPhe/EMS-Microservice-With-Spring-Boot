package com.xenia.employeemanagementsystem.repository;

import com.xenia.employeemanagementsystem.dto.response.TaskResponse;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import com.xenia.employeemanagementsystem.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends EntityRepository<Task, TaskFilter, TaskResponse> {

    @Override
    protected Class<Task> getEntityType() {
        return Task.class;
    }
}
