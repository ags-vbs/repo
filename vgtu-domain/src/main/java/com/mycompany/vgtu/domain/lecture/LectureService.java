package com.mycompany.vgtu.domain.lecture;

import java.util.List;

public interface LectureService {

    LectureJpa loadById(long id);

    LectureJpa saveNewVideoLecture(LectureJpa videoLecture);

    List<LectureJpa> loadAllVideoLectures();
    
    List<LectureJpa> loadAllVideoLecturesByCategory(Long categoryId);
}
