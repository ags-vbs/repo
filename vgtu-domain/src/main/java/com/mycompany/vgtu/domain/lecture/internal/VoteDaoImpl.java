package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.VoteJpa;
import javax.persistence.TypedQuery;

@Singleton
@Transactional
public class VoteDaoImpl extends BasicDaoImpl<VoteJpa, Long> implements VoteDao {

    VoteDaoImpl() {
        super(VoteJpa.class);
    }

    @Override
    public Double getAverageVoteForLecture(Long lectureId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select AVG(v.vote) from ");
        queryBuilder.append(VoteJpa.class.getSimpleName());
        queryBuilder.append(" v ");
        queryBuilder.append("where v.lecture.id = :lectureId");
        TypedQuery<Double> query = em().createQuery(queryBuilder.toString(), Double.class);
        query.setParameter("lectureId", lectureId);
        return query.getSingleResult();
    }

    @Override
    public boolean hasUserAlreadyVotedForLecture(Long userId, Long lectureId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select count(v.id) from ");
        queryBuilder.append(VoteJpa.class.getSimpleName());
        queryBuilder.append(" v ");
        queryBuilder.append("where v.lecture.id = :lectureId ");
        queryBuilder.append("and v.voter.id = :voterId");
        TypedQuery<Long> query = em().createQuery(queryBuilder.toString(), Long.class);
        query.setParameter("lectureId", lectureId);
        query.setParameter("voterId", userId);
        return query.getSingleResult().intValue() > 0 ? true : false;
    }

    @Override
    public void deleteAllVotesByLectureId(Long lectureId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("delete from ");
        queryBuilder.append(VoteJpa.class.getSimpleName());
        queryBuilder.append(" v ");
        queryBuilder.append("where v.lecture.id = :lectureId ");
        TypedQuery<Void> query = em().createQuery(queryBuilder.toString(), Void.class);
        query.setParameter("lectureId", lectureId);
        query.executeUpdate();
    }
}
