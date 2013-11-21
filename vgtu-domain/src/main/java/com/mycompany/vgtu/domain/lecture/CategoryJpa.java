package com.mycompany.vgtu.domain.lecture;

import com.mycompany.vgtu.domain.BasicEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryJpa extends BasicEntity {

    private static final long serialVersionUID = 1L;
    private String name;

    public CategoryJpa() {
    }

    public CategoryJpa(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
