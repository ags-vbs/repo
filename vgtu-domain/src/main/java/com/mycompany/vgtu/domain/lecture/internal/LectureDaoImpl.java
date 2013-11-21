package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import java.util.List;
import javax.persistence.TypedQuery;

@Singleton
@Transactional
public class LectureDaoImpl extends BasicDaoImpl<LectureJpa, Long> implements LectureDao {

    LectureDaoImpl() {
        super(LectureJpa.class);
    }

    @Override
    public List<LectureJpa> loadAllVideoLecturesByCategory(Long categoryId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select l from ");
        queryBuilder.append(LectureJpa.class.getSimpleName());
        queryBuilder.append(" l ");
        queryBuilder.append("where l.category.id = :categoryId");
        TypedQuery<LectureJpa> query = em().createQuery(queryBuilder.toString(), LectureJpa.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }
}
