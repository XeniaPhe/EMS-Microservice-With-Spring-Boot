package com.xenia.employeemanagementsystem.repository;

import com.xenia.employeemanagementsystem.dto.response.IEntityResponse;
import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.model.EntityBase;
import com.xenia.employeemanagementsystem.utility.DTOUtility;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class EntityRepository<T extends EntityBase, U extends EntityFilter, V extends IEntityResponse> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityType();

    private Class<V> getResponseType(){
        return (Class<V>) DTOUtility.getResponseType(getEntityType());
    }

    protected Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    public T findById(int id){
        return entityManager.find(getEntityType(), id);
    }

    public boolean exists(int id){
        return findById(id) != null;
    }

    public T save(T entity) {
        if (entity.getId() == 0) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    public boolean deleteById(int id) {
        T entity = findById(id);

        if (entity != null) {
            entity = entityManager.contains(entity) ? entity : entityManager.merge(entity);
            entityManager.remove(entity);
            return true;
        }

        return false;
    }

    public void deleteAll(List<Integer> idList) {
        String hql = "DELETE FROM " + getEntityType().getSimpleName() + " e WHERE e.id IN :idList";
        entityManager.createQuery(hql).setParameter("idList", idList).executeUpdate();
    }


    public List<V> findAll() {
        Class<V> responseClass = getResponseType();
        String hql = DTOUtility.getConstructorExpressionForHQL(responseClass);
        TypedQuery<V> query = entityManager.createQuery(hql, responseClass);

        return query.getResultList();
    }

    public List<V> findAll(int pageIndex, int pageSize) {
        Class<V> responseClass = getResponseType();
        String hql = DTOUtility.getConstructorExpressionForHQL(responseClass);
        TypedQuery<V> query = entityManager.createQuery(hql, responseClass);

        query.setFirstResult(pageIndex * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    private TypedQuery<V> getQuery(U filter){
        String hql = filter.toHQL();

        TypedQuery<V> query = entityManager.createQuery(hql, getResponseType());

        Iterator<Map.Entry<String, Object>> iterator = filter.getParameters().entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query;
    }

    public List<V> query(U filter){
        TypedQuery<V> query = getQuery(filter);
        return query.getResultList();
    }

    public List<V> query(U filter, int pageIndex, int pageSize){
        TypedQuery<V> query = getQuery(filter);
        query.setFirstResult(pageIndex * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public V querySingle(U filter, boolean expectMultipleResults){
        TypedQuery<V> query = getQuery(filter);

        if(expectMultipleResults){
            List<V> results = query.getResultList();

            if(results.size() > 0)
                return results.get(0);
        } else{
            try {
                return query.getSingleResult();
            } catch (NoResultException exception){ }
        }

        return null;
    }
}
