package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDao videoLectureCategoryDao;

    @Override
    public CategoryJpa saveNewVideoLectureCategory(CategoryJpa videoLecture) {
        return videoLectureCategoryDao.save(videoLecture);
    }

    @Override
    public CategoryJpa loadById(Long id) {
        return videoLectureCategoryDao.loadById(id);
    }

    @Override
    public List<CategoryJpa> loaddAllVideoLectureCategories() {
        return videoLectureCategoryDao.loadAll();
    }

    @Override
    public void deleteById(Long id) {
        videoLectureCategoryDao.deleteById(id);
    }
}
