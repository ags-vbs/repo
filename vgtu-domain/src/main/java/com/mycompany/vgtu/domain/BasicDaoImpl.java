package com.mycompany.vgtu.domain;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@Singleton
@Transactional
public class BasicDaoImpl<T extends BasicEntity, I extends Number> implements BasicDao<T, I> {

    @Inject
    private Provider<EntityManager> em;
    private Class<T> entityClass;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        if (entity == null) {
            return null;
        } else {
            return em().merge(entity);
        }
    }

    @Override
    public void delete(T entity) {
        if (entity == null) {
            //do nothing
        } else {
            entity = em().merge(entity);
            em().remove(entity);
        }
    }

    @Override
    public void deleteById(I id) {
        if (id == null) {
            //do nothing
        } else {
            Query query = em().createQuery(createDeletionQuery());
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }

    private String createDeletionQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ");
        builder.append(entityClass.getSimpleName());
        builder.append(" e");
        builder.append(" where a.id = :id");
        return builder.toString();
    }

    @Override
    public List<T> loadAll() {
        Query query = em().createQuery(createLoadAllQuery());
        return query.getResultList();
    }

    private String createLoadAllQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("select e from ");
        builder.append(entityClass.getSimpleName());
        builder.append(" e");
        return builder.toString();
    }

    @Override
    public T loadById(I id) {
        return em().find(entityClass, id);
    }

    protected EntityManager em() {
        return em.get();
    }
}
