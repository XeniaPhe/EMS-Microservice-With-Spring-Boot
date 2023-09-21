package com.xenia.employeemanagementsystem.utility;

import com.xenia.employeemanagementsystem.exceptions.impl.ResourceNotFoundException;
import com.xenia.employeemanagementsystem.model.EntityBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.Map;

public class RepositoryUtility {
    private RepositoryUtility(){

    }
    public static <T extends EntityBase> T fetchEntity(EntityManager entityManager, Class<? extends EntityBase> entityType, int entityId){
        EntityBase entity = entityManager.find(entityType, entityId);

        if(entity == null)
            throw ResourceNotFoundException.entityNotFoundException(entityType, entityId);

        return (T)entity;
    }

    /**
     * Initializes a lazy initialized field of an entity
     * <p>
     * Warning: This method expects the object containing the lazy initialized field to be in managed state,
     * otherwise it will throw a {@code LazyInitializationException}
     * </p>
     *
     * @param lazyInitializedField Any lazy initialized field of an entity
     * @throws LazyInitializationException if the parent object of the lazy initialized field is detached
     */
    public static void initializeField(Object lazyInitializedField){
        if(!Hibernate.isInitialized(lazyInitializedField))
            Hibernate.initialize(lazyInitializedField);
    }
}
