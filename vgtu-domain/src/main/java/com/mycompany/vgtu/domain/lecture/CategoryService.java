package com.mycompany.vgtu.domain.lecture;

import java.util.List;

public interface CategoryService {

    CategoryJpa loadById(Long id);

    CategoryJpa saveNewVideoLectureCategory(CategoryJpa videoLecture);

    List<CategoryJpa> loaddAllVideoLectureCategories();
    
    void deleteById(Long id);
}
