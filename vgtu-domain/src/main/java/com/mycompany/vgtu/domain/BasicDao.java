package com.mycompany.vgtu.domain;

import java.util.List;


public interface BasicDao<T extends BasicEntity, I extends Number> {

    void delete(T entity);

    void deleteById(I id);

    List<T> loadAll();

    T loadById(I id);

    T save(T entity);
    
}
