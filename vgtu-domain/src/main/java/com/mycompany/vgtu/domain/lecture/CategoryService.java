package com.mycompany.vgtu.domain.lecture;

import java.util.List;

public interface CategoryService {

    CategoryJpa loadById(long id);

    CategoryJpa saveNewVideoLectureCategory(CategoryJpa videoLecture);

    List<CategoryJpa> loaddAllVideoLectureCategories();
}
