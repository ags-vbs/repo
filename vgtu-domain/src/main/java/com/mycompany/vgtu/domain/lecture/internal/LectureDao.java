package com.mycompany.vgtu.domain.lecture.internal;

import com.mycompany.vgtu.domain.BasicDao;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import java.util.List;

public interface LectureDao extends BasicDao<LectureJpa, Long> {

    List<LectureJpa> loadAllVideoLecturesByCategory(Long categoryId);
}
