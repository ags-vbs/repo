package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;

@Singleton
@Transactional
class VideoLectureDaoImpl extends BasicDaoImpl<VideoLectureJpa, Long> implements VideoLectureDao {

    VideoLectureDaoImpl() {
        super(VideoLectureJpa.class);
    }
}
