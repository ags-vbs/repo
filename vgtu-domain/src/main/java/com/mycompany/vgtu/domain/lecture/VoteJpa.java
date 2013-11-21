package com.mycompany.vgtu.domain.lecture;

import com.mycompany.vgtu.domain.BasicEntity;
import com.mycompany.vgtu.domain.user.UserJpa;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class VoteJpa extends BasicEntity {

    private static final long serialVersionUID = 1L;

    public VoteJpa() {
    }

    public VoteJpa(int vote, UserJpa voter, LectureJpa lecture) {
        this.vote = vote;
        this.voter = voter;
        this.lecture = lecture;
    }
    private int vote;
    @ManyToOne
    private UserJpa voter;
    @ManyToOne
    private LectureJpa lecture;
}
