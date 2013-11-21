package com.mycompany.vgtu.domain.lecture.internal;

import com.mycompany.vgtu.domain.BasicDao;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;
import java.util.List;

public interface VideoLectureDao extends BasicDao<VideoLectureJpa, Long> {

    List<VideoLectureJpa> loadAllVideoLecturesByCategory(Long categoryId);
}
