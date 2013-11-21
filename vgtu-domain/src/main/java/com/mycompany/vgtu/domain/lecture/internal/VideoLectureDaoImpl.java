package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;
import java.util.List;
import javax.persistence.TypedQuery;

@Singleton
@Transactional
public class VideoLectureDaoImpl extends BasicDaoImpl<VideoLectureJpa, Long> implements VideoLectureDao {

    VideoLectureDaoImpl() {
        super(VideoLectureJpa.class);
    }

    @Override
    public List<VideoLectureJpa> loadAllVideoLecturesByCategory(Long categoryId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select l from ");
        queryBuilder.append(VideoLectureJpa.class.getSimpleName());
        queryBuilder.append(" l ");
        queryBuilder.append("where l.category.id = :categoryId");
        TypedQuery<VideoLectureJpa> query = em().createQuery(queryBuilder.toString(), VideoLectureJpa.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }
}
