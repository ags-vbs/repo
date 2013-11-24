package com.mycompany.vgtu.domain.lecture.internal;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.domain.lecture.VoteService;
import com.mycompany.vgtu.domain.security.Permissions;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Inject
    private ShiroAuthorizationService authorizationService;
    @Inject
    private LectureDao videoLectureDao;
    @Inject
    private UserService userService;
    @Inject
    private VoteService voteService;

    @Override
    public LectureJpa saveNewVideoLecture(LectureJpa videoLecture) {
        return videoLectureDao.save(videoLecture);
    }

    @Override
    public LectureJpa loadById(long id) {
        return videoLectureDao.loadById(id);
    }

    @Override
    public List<LectureJpa> loadAllVideoLectures() {
        return videoLectureDao.loadAll();
    }

    @Override
    public List<LectureJpa> loadAllVideoLecturesByCategory(Long categoryId) {
        return videoLectureDao.loadAllVideoLecturesByCategory(categoryId);
    }

    //FIX ME. Make this method nicer.
    @Override
    public boolean canLectureBeDeletedByCurrentUser(LectureJpa videoLecture) {
        Optional<UserJpa> currentUser = userService.loadCurrentUser();
        boolean isUserAdmin = authorizationService.isPermittedAllOf(Permissions.ADMIN);
        if (isUserAdmin) {
            return true;
        } else {
            if (currentUser.isPresent()) {
                if (currentUser.get().getId().equals(videoLecture.getUploader().getId())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        voteService.deleteAllVotesByLectureId(id);
        videoLectureDao.deleteById(id);
    }
}
