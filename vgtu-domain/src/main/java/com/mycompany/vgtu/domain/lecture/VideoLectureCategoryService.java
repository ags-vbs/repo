package com.mycompany.vgtu.domain.lecture;

import java.util.List;

public interface VideoLectureCategoryService {

    VideoLectureCategoryJpa loadById(long id);

    VideoLectureCategoryJpa saveNewVideoLectureCategory(VideoLectureCategoryJpa videoLecture);

    List<VideoLectureCategoryJpa> loaddAllVideoLectureCategories();
}
