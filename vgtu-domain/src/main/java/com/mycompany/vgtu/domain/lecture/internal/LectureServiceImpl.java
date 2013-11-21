package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Inject
    private LectureDao videoLectureDao;

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
}
