package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureService;
import java.util.List;

public class VideoLectureServiceImpl implements VideoLectureService {

    @Inject
    private VideoLectureDao videoLectureDao;

    @Override
    public VideoLectureJpa saveNewVideoLecture(VideoLectureJpa videoLecture) {
        return videoLectureDao.save(videoLecture);
    }

    @Override
    public VideoLectureJpa loadById(long id) {
        return videoLectureDao.loadById(id);
    }

    @Override
    public List<VideoLectureJpa> loadAllVideoLectures() {
        return videoLectureDao.loadAll();
    }

    @Override
    public List<VideoLectureJpa> loadAllVideoLecturesByCategory(Long categoryId) {
        return videoLectureDao.loadAllVideoLecturesByCategory(categoryId);
    }
}
