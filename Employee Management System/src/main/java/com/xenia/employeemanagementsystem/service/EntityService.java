package com.xenia.employeemanagementsystem.service;

import com.xenia.employeemanagementsystem.dto.request.create.ICreateRequest;
import com.xenia.employeemanagementsystem.dto.request.update.IUpdateRequest;
import com.xenia.employeemanagementsystem.dto.response.IEntityResponse;
import com.xenia.employeemanagementsystem.exceptions.impl.ResourceNotFoundException;
import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.model.EntityBase;
import com.xenia.employeemanagementsystem.repository.EntityRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class EntityService<S extends EntityBase, T extends ICreateRequest, U extends IUpdateRequest,
        V extends IEntityResponse, Y extends EntityFilter, Z extends EntityRepository<S, Y, V>> {

    protected abstract Class<S> getEntityType();
    protected abstract Z getRepository();
    protected abstract V convertToResponse(S entity);
    protected abstract S convertToEntity(T createRequest);
    protected abstract S convertToEntity(U updateRequest);


    protected S getById(int id){
        S entity = getRepository().findById(id);

        if(entity == null)
            throw ResourceNotFoundException.entityNotFoundException(getEntityType(), id);

        return entity;
    }

    protected <Z extends EntityBase> Z getById(EntityService<Z,?,?,?,?,?> entityService, int id){
        return entityService.getById(id);
    }

    protected V save(S entity){
        return convertToResponse(getRepository().save(entity));
    }

    public V add(T createRequest){
        return save(convertToEntity(createRequest));
    }

    public V update(U updateRequest){
        return save(convertToEntity(updateRequest));
    }

    public V findById(int id){
        return convertToResponse(getById(id));
    }

    public boolean exists(int id){
        return getRepository().exists(id);
    }

    public void deleteById(int id){
        if(!getRepository().deleteById(id))
            throw ResourceNotFoundException.entityNotFoundException(getEntityType(), id);
    }

    public void deleteAll(List<Integer> idList){
        getRepository().deleteAll(idList);
    }

    public List<V> findAll(){
        return getRepository().findAll();
    }

    public List<V> findAll(int pageIndex, int pageSize){
        return getRepository().findAll(pageIndex, pageSize);
    }
}
