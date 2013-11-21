package com.mycompany.vgtu.domain.lecture;

import com.mycompany.vgtu.domain.BasicEntity;
import com.mycompany.vgtu.domain.user.UserJpa;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lectures")
public class LectureJpa extends BasicEntity {

    private static final long serialVersionUID = 1L;
    private String url;
    private String description;
    private String name;
    @ManyToOne
    private CategoryJpa category;
    @ManyToOne
    private UserJpa uploader;

    public LectureJpa() {
    }

    public LectureJpa(String url, String description, String name, UserJpa uploader) {
        this.url = url;
        this.description = description;
        this.name = name;
        this.uploader = uploader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserJpa getUploader() {
        return uploader;
    }

    public void setUploader(UserJpa uploader) {
        this.uploader = uploader;
    }

    public CategoryJpa getCategory() {
        return category;
    }

    public void setCategory(CategoryJpa category) {
        this.category = category;
    }
}
