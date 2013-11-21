package com.mycompany.vgtu.domain.lecture;

public interface VoteService {

    VoteJpa saveNewVote(VoteJpa vote);

    Double getAverageVoteForLecture(Long lectureId);

    boolean hasUserAlreadyVotedForLecture(Long userId, Long lectureId);
}
