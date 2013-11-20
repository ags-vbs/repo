package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryService;

class VideoLectureCategoryServiceImpl implements VideoLectureCategoryService {

    @Inject
    private VideoLectureCategoryDao videoLectureCategoryDao;

    @Override
    public VideoLectureCategoryJpa saveNewVideoLectureCategory(VideoLectureCategoryJpa videoLecture) {
        return videoLectureCategoryDao.save(videoLecture);
    }

    @Override
    public VideoLectureCategoryJpa loadById(long id) {
        return videoLectureCategoryDao.loadById(id);
    }
}
