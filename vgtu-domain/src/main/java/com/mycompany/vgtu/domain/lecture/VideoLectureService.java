package com.mycompany.vgtu.domain.lecture;

import java.util.List;

public interface VideoLectureService {

    VideoLectureJpa loadById(long id);

    VideoLectureJpa saveNewVideoLecture(VideoLectureJpa videoLecture);

    List<VideoLectureJpa> loadAllVideoLectures();
    
    List<VideoLectureJpa> loadAllVideoLecturesByCategory(Long categoryId);
}
