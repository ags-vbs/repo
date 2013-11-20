package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;

@Singleton
@Transactional
public class VideoLectureCategoryDaoImpl extends BasicDaoImpl<VideoLectureCategoryJpa, Long> implements VideoLectureCategoryDao {

    VideoLectureCategoryDaoImpl() {
        super(VideoLectureCategoryJpa.class);
    }
}
