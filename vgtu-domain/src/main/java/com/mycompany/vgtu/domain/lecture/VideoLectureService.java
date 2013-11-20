package com.mycompany.vgtu.domain.lecture;

public interface VideoLectureService {

    VideoLectureJpa loadById(long id);

    VideoLectureJpa saveNewVideoLecture(VideoLectureJpa videoLecture);
}
