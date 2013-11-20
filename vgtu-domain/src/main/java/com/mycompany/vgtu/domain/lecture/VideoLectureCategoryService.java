package com.mycompany.vgtu.domain.lecture;

public interface VideoLectureCategoryService {

    VideoLectureCategoryJpa loadById(long id);

    VideoLectureCategoryJpa saveNewVideoLectureCategory(VideoLectureCategoryJpa videoLecture);
}
