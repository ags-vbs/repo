package com.mycompany.vgtu.domain.lecture.internal;

import com.mycompany.vgtu.domain.BasicDao;
import com.mycompany.vgtu.domain.lecture.VoteJpa;

public interface VoteDao extends BasicDao<VoteJpa, Long> {

    Double getAverageVoteForLecture(Long lectureId);

    boolean hasUserAlreadyVotedForLecture(Long userId, Long lectureId);
}
