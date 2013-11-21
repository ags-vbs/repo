package com.mycompany.vgtu.domain.lecture.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;

@Singleton
@Transactional
public class CategoryDaoImpl extends BasicDaoImpl<CategoryJpa, Long> implements CategoryDao {

    CategoryDaoImpl() {
        super(CategoryJpa.class);
    }
}
