package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VoteJpa;
import com.mycompany.vgtu.domain.lecture.VoteService;

public class VoteServiceImpl implements VoteService {

    @Inject
    private VoteDao voteDao;

    @Override
    public VoteJpa saveNewVote(VoteJpa vote) {
        return voteDao.save(vote);
    }

    @Override
    public Double getAverageVoteForLecture(Long lectureId) {
        return voteDao.getAverageVoteForLecture(lectureId);
    }

    @Override
    public boolean hasUserAlreadyVotedForLecture(Long userId, Long lectureId) {
        return voteDao.hasUserAlreadyVotedForLecture(userId, lectureId);
    }

    @Override
    public void deleteAllVotesByLectureId(Long lectureId) {
        voteDao.deleteAllVotesByLectureId(lectureId);
    }
}
